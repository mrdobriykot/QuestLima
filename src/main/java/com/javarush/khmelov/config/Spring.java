package com.javarush.khmelov.config;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.*;
import net.bytebuddy.matcher.ElementMatchers;

import javax.transaction.Transactional;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class Spring {
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

    private <T> boolean checkTransactional(Class<T> type) {
        return Arrays.stream(type.getMethods())
                .anyMatch(method -> method.isAnnotationPresent(Transactional.class));
    }

    @SneakyThrows
    private Object proxyTransactional(Object component, Class<?>[] parameterTypes, Object[] parameters) {
        Class<?> type = component.getClass();
        Class<?> proxy = new ByteBuddy()
                .subclass(type)
                .method(ElementMatchers.isAnnotatedWith(Transactional.class))
                .intercept(MethodDelegation.to(Interceptor.class))
                .make()
                .load(type.getClassLoader())
                .getLoaded();
        Constructor<?> constructor = proxy.getConstructor(parameterTypes);
        return constructor.newInstance(parameters);
    }

    public class Interceptor {

        private static final SessionCreator sessionCreator = getBean(SessionCreator.class);

        @RuntimeType
        public static Object intercept(@This Object self,
                                       @Origin Method method,
                                       @AllArguments Object[] args,
                                       @SuperMethod Method superMethod) throws Throwable {
            sessionCreator.beginTransactional();
            try {
                return superMethod.invoke(self, args);
            } finally {
                sessionCreator.endTransactional();
            }
        }
    }
}
