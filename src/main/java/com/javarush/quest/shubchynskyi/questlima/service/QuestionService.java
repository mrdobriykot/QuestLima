package com.javarush.quest.shubchynskyi.questlima.service;

import com.javarush.quest.shubchynskyi.questlima.config.Config;
import com.javarush.quest.shubchynskyi.questlima.entity.game.Question;

import java.util.Optional;

public enum QuestionService {

    QUESTION_SERVICE;

    private final Config config = Config.CONFIG;

    @SuppressWarnings("unused")
    public Optional<Question> get(Long id) {
        return config.questionRepository.find(Question.builder().id(id).build()).findAny();
    }

    public Optional<Question> get(String id) {
        return config.questionRepository.find(Question.builder().id(Long.valueOf(id)).build()).findAny();
    }

    public void create(Question question) {
        config.questionRepository.create(question);
    }

    public void update(Question question) {
        config.questionRepository.update(question);
    }

    @SuppressWarnings("unused")
    public void delete(Question question) {
        config.questionRepository.delete(question);
    }
}
