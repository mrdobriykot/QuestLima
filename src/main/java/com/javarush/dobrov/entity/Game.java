package com.javarush.dobrov.entity;

import java.util.concurrent.atomic.AtomicLong;

public class Game implements AbstractEntity {

    private Long gameId;
    private Long questId;
    private Long questionId;

    public Game(Long gameId, Long questId, Long questionId) {
        this.gameId = gameId;
        this.questId = questId;
        this.questionId = questionId;
    }

    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }


    @Override
    public void setId(long id) {
        gameId = id;
    }

    @Override
    public Long getId() {
        return gameId;
    }

}
