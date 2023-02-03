package com.javarush.dobrov.entity;

import java.util.concurrent.atomic.AtomicLong;

public class Answer implements AbstractEntity {

    private long questionId;
    private long answerId;
    private String answerText;
    private Long answer_number;

    public Answer(String answerText) {
        this.answerText = answerText;

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

    public Long getAnswer_number() {
        return answer_number;
    }

    public void setAnswer_number(Long answer_number) {
        this.answer_number = answer_number;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerNumber=" + answer_number +
                ", answerId=" + answerId +
                ", answerText='" + answerText + '\'' +
                '}';
    }
}
