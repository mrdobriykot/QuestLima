package com.javarush.quest.ivanilov.repositories;

import com.javarush.quest.ivanilov.entities.game.Game;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Optional;

public class GameRepository extends BaseRepository<Game> {
    @Override
    public Optional<Game> find(String pattern) {
        throw new NotImplementedException();
    }
}
