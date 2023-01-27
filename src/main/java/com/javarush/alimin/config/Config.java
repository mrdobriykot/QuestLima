package com.javarush.alimin.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.javarush.alimin.entity.Question;
import com.javarush.alimin.exception.AppException;
import com.javarush.alimin.service.QuestionService;

import java.io.IOException;
import java.util.Collection;

public class Config {

    public static void setQuestContentFromJson() {
        QuestionService questionService = QuestionService.QUESTION_SERVICE;
        ObjectMapper mapper = new JsonMapper();
        try {
            Collection<Question> questions = mapper.readValue(Config.class.getResource("/quest_content.json"),
                    new TypeReference<>() {});
            for (Question question : questions) {
                questionService.create(question);
            }
        } catch (IOException e) {
            throw new AppException(e);
        }
    }
}
