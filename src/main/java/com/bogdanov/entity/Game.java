package com.bogdanov.entity;

public class Game implements AbstaractEntity {
    private Long id;
    private Long questionId;
    private Long userId;
    private Long currentQuestionId;
    private GameState gameState;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
