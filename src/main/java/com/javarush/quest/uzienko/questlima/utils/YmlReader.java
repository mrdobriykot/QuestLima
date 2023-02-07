package com.javarush.quest.uzienko.questlima.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.quest.uzienko.questlima.entity.Entity;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public final class YmlReader {
    private YmlReader() {
    }

    public static <T extends Entity> List<T> getData(String fileName, Class<T> type) {
        ObjectMapper mapper = new YAMLMapper();
        File file = getFile(fileName);
        try {
            JavaType javaType = mapper.getTypeFactory()
                    .constructParametricType(List.class, type);
            return mapper.readValue(file, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static File getFile(String fileName) {
        URL resource = YmlReader.class.getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }
        return new File(resource.getFile());
    }
}
