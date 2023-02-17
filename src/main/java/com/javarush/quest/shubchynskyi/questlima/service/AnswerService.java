package com.javarush.quest.shubchynskyi.questlima.service;

import com.javarush.quest.shubchynskyi.questlima.config.Config;
import com.javarush.quest.shubchynskyi.questlima.entity.game.Answer;

public enum AnswerService {

    ANSWER_SERVICE;

    private final Config config = Config.CONFIG;

    @SuppressWarnings("unused")
    public void create(Answer answer) {
        config.answerRepository.create(answer);
    }

    public void update(Answer answer) {
        config.answerRepository.update(answer);
    }

    @SuppressWarnings("unused")
    public void delete(Answer answer) {
        config.answerRepository.delete(answer);
    }
}
