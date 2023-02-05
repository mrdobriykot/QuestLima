package com.javarush.quest.khlopin.questlima.entity.game;

import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game extends GameEntity {

    private Quest quest;
    private GameState gameState;


}
