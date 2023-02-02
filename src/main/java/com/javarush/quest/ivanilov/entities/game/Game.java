package com.javarush.quest.ivanilov.entities.game;

import com.javarush.quest.ivanilov.entities.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Game extends AbstractEntity {
    private long playerId;
    private long questId;
    private long currEventId;
    private Hero hero;
    private GameStatus status;

}
