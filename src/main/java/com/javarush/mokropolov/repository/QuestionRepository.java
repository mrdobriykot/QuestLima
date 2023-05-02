package com.javarush.mokropolov.repository;

import java.util.stream.Stream;

import com.javarush.mokropolov.entity.Question;

public class QuestionRepository extends BaseRepository<Question> {
    @Override
    public Stream<Question> find(Question pattern) {
        return map.values()
                .stream()
                .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                .filter(u -> nullOrEquals(pattern.getQuestId(), u.getQuestId()))
                .filter(u -> nullOrEquals(pattern.getText(), u.getText()))
                .filter(u -> nullOrEquals(pattern.getGameState(), u.getGameState()));
    }
}