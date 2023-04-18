package com.javarush.khmelov.config;

import com.javarush.khmelov.repository.helper.SessionCreator;
import lombok.experimental.UtilityClass;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import javax.transaction.Transactional;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class Winter {
    private final Map<Class<?>, Object> container = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> type) { //QuestService.class
        try {
            if (container.containsKey(type)) {
                return (T) container.get(type);
            } else {
                Constructor<?>[] constructors = type.getConstructors();
                Constructor<?> constructor = constructors[0];
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Object[] parameters = new Object[parameterTypes.length];
                for (int i = 0; i < parameters.length; i++) {
                    parameters[i] = getBean(parameterTypes[i]);
                }
                Object component = constructor.newInstance(parameters);
                if (checkTransactional(type)) {
                    component = proxyTransactional(component, parameterTypes, parameters);
                }
                container.put(type, component);
                return (T) component;
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Context broken for " + type, e);
        }
    }

    private static <T> boolean checkTransactional(Class<T> type) {
        if (type.isAnnotationPresent(Transactional.class)) {
            return true;
        }
        for (Method declaredMethod : type.getDeclaredMethods()) {
            if (declaredMethod.isAnnotationPresent(Transactional.class)) {
                return true;
            }
        }
        return false;
    }


    private Object proxyTransactional(Object component, Class<?>[] parameterTypes, Object[] parameters) {
        SessionCreator sessionCreator = getBean(SessionCreator.class);
        Enhancer enhancer = new Enhancer();
        Class<?> type = component.getClass();
        enhancer.setSuperclass(type);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
                    if (type.isAnnotationPresent(Transactional.class)
                        || method.isAnnotationPresent(Transactional.class)) {
                        sessionCreator.beginTransactional();
                        try {
                            return proxy.invokeSuper(obj, args);
                        } finally {
                            sessionCreator.endTransactional();
                        }
                    } else {
                        return proxy.invokeSuper(obj, args);
                    }
                }
        );
        return enhancer.create(parameterTypes, parameters);
    }

}
