package com.javarush.quest.ivanilov.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.ivanilov.controllers.IndexServlet;

import java.io.InputStream;

public class Json<T> {
    private final Class<T> type;

    public Json(Class<T> type) {
        this.type = type;
    }

    private Class<T> getType() {
        return this.type;
    }

    public T readJson(String path) {
        InputStream stream = IndexServlet.class.getResourceAsStream(path);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(stream, getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
