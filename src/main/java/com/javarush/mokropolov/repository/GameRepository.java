package com.javarush.mokropolov.repository;



import com.javarush.mokropolov.entity.Game;
import com.javarush.mokropolov.entity.Game;

import java.util.stream.Stream;

public class GameRepository extends BaseRepository<Game> {


    @Override
    public Stream<Game> find(Game pattern) {
        return map.values()
                .stream()
                .filter(game -> nullOrEquals(pattern.getId(), game.getId()))
                .filter(game -> nullOrEquals(pattern.getUserId(), game.getUserId()))
                .filter(game -> nullOrEquals(pattern.getQuestionId(), game.getQuestionId()))
                .filter(game -> nullOrEquals(pattern.getCurrentQuestId(), game.getCurrentQuestId()))
                .filter(game -> nullOrEquals(pattern.getGameState(), game.getGameState()));

    }



}

