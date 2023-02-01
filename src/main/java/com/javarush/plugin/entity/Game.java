package com.javarush.plugin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {

    private Long id;
    private Long questId;
    private Long userId;
    private Long currentQuestionId;
    private GameState gameState;

}
