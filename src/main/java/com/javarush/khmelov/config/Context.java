package com.javarush.khmelov.config;

import lombok.experimental.UtilityClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@UtilityClass
public class Context {

    final String BASE_PACKAGE = "com.javarush.khmelov";

    private final ApplicationContext context =
            new AnnotationConfigApplicationContext(BASE_PACKAGE);

    public <T> T getBean(Class<T> type) {
        return context.getBean(type);
    }
}
