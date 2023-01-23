package com.javarush.quest.osypenko.repository.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.osypenko.repository.DB;
import com.javarush.quest.osypenko.repository.Training;

import java.io.IOException;
import java.util.HashMap;

public class Core1 implements Training {
    private final HashMap<Long, DB> map;
    private static final Long CONSTANT_ID = 1000L;

    public Core1() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            //noinspection unchecked
            map = mapper.readValue(Core1.class.getResource("/core1.json"), HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getConstantID() {
        return CONSTANT_ID;
    }

    @Override
    public HashMap<Long, DB> getMap() {
        return map;
    }
}
