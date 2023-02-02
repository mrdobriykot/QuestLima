package com.javarush.alimin.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.javarush.alimin.entity.Question;
import com.javarush.alimin.exception.AppException;
import com.javarush.alimin.service.QuestionService;
import com.javarush.alimin.util.URLPatterns;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;

@UtilityClass
public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);

    public static void setQuestContentFromJson() {
        QuestionService questionService = QuestionService.QUESTION_SERVICE;
        ObjectMapper mapper = new JsonMapper();
        try {
            log.debug("Getting quest content from json file");
            Collection<Question> questions = mapper.readValue(Config.class.getResource(URLPatterns.QUEST_CONTENT),
                    new TypeReference<>() {});
            log.debug("Quest content received, starting to fill QuestionRepository in QuestionService");
            for (Question question : questions) {
                questionService.create(question);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new AppException(e);
        }
    }
}
