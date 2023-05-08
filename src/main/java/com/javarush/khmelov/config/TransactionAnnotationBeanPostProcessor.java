package com.javarush.khmelov.config;

import javax.transaction.Transactional;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class TransactionAnnotationBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class<?>> mapRealClass = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        if (methodIsAnnotationPresent(aClass)) {
            mapRealClass.putIfAbsent(beanName, aClass);
            System.out.printf(">>> before init %s%n", beanName);
        }
        return bean;
    }

    private boolean methodIsAnnotationPresent(Class<?> aClass) {
        return //aClass.isAnnotationPresent(Transactional.class) ||
               Arrays.stream(aClass.getMethods())
                .anyMatch(m -> m.isAnnotationPresent(Transactional.class));
    }

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        Class<?> aClass = mapRealClass.get(beanName);
        if (aClass != null) {
            bean = proxy(bean, aClass);
            System.out.printf("<<< after init %s%n", beanName);
        }
        return bean;
    }

    //WARNING!!! CGLIB NEED DEFAULT CONSTRUCTOR in beanRealClass!!!
    private Object proxy(Object beanOrProxy, Class<?> beanRealClass) {
        MethodInterceptor handler = (obj, method, args, proxy) -> {
            Object result;
            if (method.isAnnotationPresent(Transactional.class)) {
                SessionCreator sessionCreator = Context.getBean(SessionCreator.class);
                sessionCreator.beginTransactional();
                try {
                    result = proxy.invoke(beanOrProxy, args);
                } finally {
                    sessionCreator.endTransactional();
                }
            } else {
                result = proxy.invoke(beanOrProxy, args);
            }
            return result;
        };
        return Enhancer.create(beanRealClass, handler);
    }
}
