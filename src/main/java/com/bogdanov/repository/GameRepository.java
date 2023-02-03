package com.bogdanov.repository;

import com.bogdanov.entity.Game;

import java.util.stream.Stream;

public class GameRepository extends BaseRepository<Game>{

    


    @Override
    public Stream<Game> find(Game pattern) {
        return map.values()
                .stream()
                .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                .filter(u -> nullOrEquals(pattern.getQuestionId(), u.getQuestionId()))
                .filter(u -> nullOrEquals(pattern.getUserId(), u.getUserId()))
                .filter(u -> nullOrEquals(pattern.getGameState(), u.getGameState()))
                .filter(u -> nullOrEquals(pattern.getCurrentQuestionId(), u.getCurrentQuestionId()));

} 



}
