package com.javarush.dobrov.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class SingleEntity {

    private static final Map<Class<?>, Object> objects = new HashMap<>();


    public static <T> T getInstance(Class<T> entity) {
        try {
            if (objects.containsKey(entity)) {
                return (T) objects.get(entity);
            } else {

                Constructor<?>[] constructors = entity.getConstructors();
                Constructor<?> constructor = constructors[0];
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Object[]params = new Object[parameterTypes.length];
                for (int i = 0; i < params.length; i++) {
                    params[i] = getInstance(parameterTypes[i]);
                }

                    Object component = constructor.newInstance(params);
                    objects.put(entity,component);
                    return (T) component;
                }
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }
}
