package com.javarush.khmelov.config;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.*;
import net.bytebuddy.matcher.ElementMatchers;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.transaction.Transactional;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;

@UtilityClass
public class Context {

    final String BASE_PACKAGE = "com.javarush.khmelov";

    private final ApplicationContext context =
            new AnnotationConfigApplicationContext(BASE_PACKAGE);

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> type) {
        return !checkTransactional(type)
                ? context.getBean(type) //bean in Spring
                : (T) constructProxyInstance(type); //manual construct
    }

    //need move methods below to Spring framework or just use Spring Data JPA
    private <T> boolean checkTransactional(Class<T> type) {
        return type.isAnnotationPresent(Transactional.class)
               || Arrays.stream(type.getMethods())
                       .anyMatch(method -> method.isAnnotationPresent(Transactional.class));
    }

    @SneakyThrows
    private Object constructProxyInstance(Class<?> type) {
        Constructor<?> constructor = type.getConstructors()[0];
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] parameters = new Object[parameterTypes.length];
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = getBean(parameterTypes[i]);
        }
        Class<?> proxy = new ByteBuddy()
                .subclass(type)
                .method(isDeclaredBy(ElementMatchers.isAnnotatedWith(Transactional.class))
                        .or(ElementMatchers.isAnnotatedWith(Transactional.class)))
                .intercept(MethodDelegation.to(Interceptor.class))
                .make()
                .load(type.getClassLoader())
                .getLoaded();
        return proxy.getConstructor(parameterTypes).newInstance(parameters);
    }


    public static class Interceptor {
        @RuntimeType
        public static Object intercept(@This Object self,
                                       @Origin Method method,
                                       @AllArguments Object[] args,
                                       @SuperMethod Method superMethod) throws Throwable {
            getBean(SessionCreator.class).beginTransactional();
            try {
                return superMethod.invoke(self, args);
            } finally {
                getBean(SessionCreator.class).endTransactional();
            }
        }
    }
}
