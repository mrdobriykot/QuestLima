package com.bogdanov.entity;

public class Answer implements AbstaractEntity {
    private Long id;
    private Long questionId;
    private String text;
    private Long nextQuestionId;

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


}
