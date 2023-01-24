package com.javarush.quest.osypenko.repository.entityDB;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.osypenko.repository.DB;
import com.javarush.quest.osypenko.repository.Training;

import java.io.IOException;
import java.util.TreeMap;

public class Algorithms implements Training {

    private final TreeMap<Long, DB> map;

    private static final Long CONSTANT_ID = 4000L;

    public Algorithms() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            //noinspection unchecked
            map = mapper.readValue(Core1.class.getResource("/algorithms.json"), TreeMap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getConstantID() {
        return CONSTANT_ID;
    }

    @Override
    public TreeMap<Long, DB> getMap() {
        return map;
    }

}
