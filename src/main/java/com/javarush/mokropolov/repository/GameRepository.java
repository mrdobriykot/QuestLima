package com.javarush.mokropolov.repository;

import java.util.stream.Stream;

import com.javarush.mokropolov.entity.Game;

public class GameRepository extends BaseRepository<Game> {
    @Override
    public Stream<Game> find(Game pattern) {
        return map.values()
                .stream()
                .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                .filter(u -> nullOrEquals(pattern.getUserId(), u.getUserId()))
                .filter(u -> nullOrEquals(pattern.getQuestId(), u.getQuestId()))
                .filter(u -> nullOrEquals(pattern.getCurrentQuestionId(), u.getCurrentQuestionId()))
                .filter(u -> nullOrEquals(pattern.getGameState(), u.getGameState()));
    }
}

