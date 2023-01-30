package com.javarush.quest.ivanilov.services;

import com.javarush.quest.ivanilov.entities.game.Game;
import com.javarush.quest.ivanilov.repositories.GameRepository;
import com.javarush.quest.ivanilov.repositories.Repository;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Optional;

@Slf4j
public enum GameService {
    GAME_SERVICE;

    private final Repository<Game> repo = new GameRepository();

    public Collection<Game> getAll() {
        return repo.getAll();
    }

    public Game create(Game game) {
        return repo.create(game);
    }

    public Game get(long id) {
        return repo.get(id);
    }

    public Game update(Game game) {
        return repo.update(game);
    }

    public void delete(Game game) {
        repo.delete(game);
    }

    public Optional<Game> find(String pattern) {
        return repo.find(pattern);
    }

}
