package com.javarush.quest.khlopin.questlima.entity.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Game extends GameEntity {

    private Quest quest;
    private GameState gameState;


}
