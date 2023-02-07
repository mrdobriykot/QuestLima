package com.javarush.quest.ivanilov.services;

import com.javarush.quest.ivanilov.entities.game.Game;
import com.javarush.quest.ivanilov.repositories.GameRepository;
import com.javarush.quest.ivanilov.repositories.Repository;


public enum GameService {
    GAME_SERVICE;

    private final Repository<Game> repo = new GameRepository();

    public Game create(Game game) {
        return repo.create(game);
    }

    public Game get(long id) {
        return repo.get(id);
    }

    public Game update(Game game) {
        return repo.update(game);
    }

}
