package com.javarush.quest.marzhiievskyi.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameSession implements AbstractEntity{
    private Long id;
    private Long currentQuestionId;
    private Long userId;
    private Long questId;
    private GameState gameState;
}
