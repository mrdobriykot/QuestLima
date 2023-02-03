package com.bogdanov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game implements AbstaractEntity {
    private Long id;
    private Long questionId;
    private Long userId;
    private Long currentQuestionId;
    private GameState gameState;
}


