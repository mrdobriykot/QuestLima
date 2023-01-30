package com.javarush.dobrov.entity;

import java.util.concurrent.atomic.AtomicLong;

public class Answer implements AbstractEntity {

    private long questionId;
    private long answerId;
    private String answerText;

    public Answer(String answerText,long questionId) {
        this.answerText = answerText;
        this.questionId = questionId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    @Override
    public void setId(long id) {
        answerId = id;
    }

    @Override
    public Long getId() {
        return answerId;
    }


    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
