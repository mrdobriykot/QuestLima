package com.javarush.quest.khlopin.questlima.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.khlopin.questlima.entity.game.Quest;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.ArrayList;


public class UploadQuestFromJson {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String[] pathsToJsons;


    public UploadQuestFromJson(String[] pathsToJsons) {
        this.pathsToJsons = pathsToJsons;
    }

    //TODO вывести строки в константы или сделать так, чтобы на вход надо было вводить String json
    //TODO сделать возможность изменять путь к файлу для загрузки(загружать другие квесты)

    @SneakyThrows
    public ArrayList<Quest> readJsonsAndCreateQuests() {
        ArrayList<Quest> quests = new ArrayList<>();
        for (String pathsToJson : pathsToJsons) {
            InputStream stream = UploadQuestFromJson.class.getResourceAsStream(pathsToJson);
            Quest quest = objectMapper.readValue(stream, Quest.class);
            quests.add(quest);
        }
        return quests;
    }
}
