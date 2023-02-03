package com.javarush.quest.uzienko.questlima.config;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
@UtilityClass
public class Winter {
    private final Map<Class<?>, Object> container = new HashMap<>();
    private static final String CONTEXT_BROKEN_ERROR = "Context broken for ";

    public <T> T getBean(Class<T> type) {
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
                container.put(type, component);
                return (T) component;
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(CONTEXT_BROKEN_ERROR + type, e);
        }
    }

}
