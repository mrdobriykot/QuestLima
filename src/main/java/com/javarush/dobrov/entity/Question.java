package com.javarush.dobrov.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Question implements AbstractEntity {

    private String questionText;

    private Long questId;
    private Long questionId;

    Map<Long, String> answers = new HashMap<>();

    public Question(String questionText, Long questId) {
        this.questionText = questionText;

        this.questId = questId;
    }


    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
    }

    @Override
    public void setId(long id) {
        questionId = id;
    }

    @Override
    public Long getId() {
        return questionId;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                ", questId=" + questId +
                "questionId=" + questionId +
                '}';
    }
}
