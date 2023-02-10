package com.javarush.quest.khlopin.questlima.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.khlopin.questlima.entity.game.Quest;
import lombok.SneakyThrows;
import java.io.InputStream;


public class UploadQuestFromJson {

    public static ObjectMapper objectMapper = new ObjectMapper();

    //TODO вывести строки в константы или сделать так чтобы на вход надо было вводить String json
    //TODO сделать возможность изменять путь к файлу для загрузки(загружать другие квесты)

    @SneakyThrows
    public Quest readJsonAndCreateQuest() {
        String json = "/quests/quest1.json";
        InputStream stream = UploadQuestFromJson.class.getResourceAsStream(json);
        try {
            return objectMapper.readValue(stream, Quest.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
