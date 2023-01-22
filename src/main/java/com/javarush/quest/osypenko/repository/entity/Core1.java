package com.javarush.quest.osypenko.repository.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.osypenko.repository.DB;
import com.javarush.quest.osypenko.repository.Training;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Core1 implements Training {
    private final HashMap<Long, DB> map;

    public Core1() {

        try {
            ObjectMapper mapper = new ObjectMapper();
            //noinspection unchecked
            map = mapper.readValue(new File("/Users/oleksandrosypenko/IdeaProjects/ProjectJavaRushUnivercity/QuestTestProject/src/main/resources/core1.json"), HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashMap<Long, DB> getMap() {
        return map;
    }

}
