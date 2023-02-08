package com.javarush.quest.khlopin.questlima.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.khlopin.questlima.entity.game.Quest;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;

public class UploadQuestFromJson {

    public static ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    public Quest readJsonAndCreateQuest() {
        String json = Files.readString(Paths.get("D:\\Projects\\QuestLima\\src\\main\\resources\\quests\\quest1.json")); //TODO заменить путь на относительный. У том кета другой путь не знаю какой
        return objectMapper.readValue(json, Quest.class);
    }
}
