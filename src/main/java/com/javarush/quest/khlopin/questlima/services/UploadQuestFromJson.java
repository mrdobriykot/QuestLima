package com.javarush.quest.khlopin.questlima.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.khlopin.questlima.entity.game.Quest;
import com.javarush.quest.khlopin.questlima.utills.DB;
import org.apache.taglibs.standard.extra.spath.Path;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UploadQuestFromJson {
    public static void main(String[] args) throws JsonProcessingException {

        System.out.println(createJson());

    }

    public void readJson() throws URISyntaxException, IOException {
        Files.readAllBytes(Paths.get(new URI("D:\\json")));
    }

    public static String createJson() throws JsonProcessingException {
        Quest quest = DB.questDataBase.get(1);

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(quest);
        return s;
    }


}
