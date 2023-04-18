package com.javarush.khmelov.repository.impl;

import com.javarush.khmelov.entity.Game;
import com.javarush.khmelov.repository.BaseRepository;
import com.javarush.khmelov.config.SessionCreator;

public class GameRepository extends BaseRepository<Game> {

    public GameRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Game.class);
    }
}
