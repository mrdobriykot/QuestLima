package com.javarush.quest.khlopin.questlima.entity.game;

import com.javarush.quest.khlopin.questlima.entity.GameEntity;
import com.javarush.quest.khlopin.questlima.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Game extends GameEntity {

    private User player;

    private Question quest;
    private GameState gameState;


}
