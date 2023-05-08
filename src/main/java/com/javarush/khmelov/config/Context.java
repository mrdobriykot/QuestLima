package com.javarush.khmelov.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Context {

    final static String BASE_PACKAGE = "com.javarush.khmelov";

    private static final ApplicationContext context =
            new AnnotationConfigApplicationContext(BASE_PACKAGE);

    public static <T> T getBean(Class<T> type) {
        return context.getBean(type);
    }

}
