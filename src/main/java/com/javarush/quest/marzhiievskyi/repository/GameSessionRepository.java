package com.javarush.quest.marzhiievskyi.repository;

import com.javarush.quest.marzhiievskyi.entity.GameSession;

import java.util.stream.Stream;

public class GameSessionRepository extends BaseRepository<GameSession> {

    @Override
    public Stream<GameSession> find(GameSession pattern) {
        return map.values().stream()
                .filter(gameSession -> nullOrEquals(pattern.getId(), gameSession.getId()))
                .filter(gameSession -> nullOrEquals(pattern.getQuestId(), gameSession.getQuestId()))
                .filter(gameSession -> nullOrEquals(pattern.getUserId(), gameSession.getUserId()))
                .filter(gameSession -> nullOrEquals(pattern.getCurrentQuestionId(), gameSession.getCurrentQuestionId()))
                .filter(gameSession -> nullOrEquals(pattern.getGameState(), gameSession.getGameState()));
    }
}
