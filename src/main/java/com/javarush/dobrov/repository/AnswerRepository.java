package com.javarush.dobrov.repository;

import com.javarush.dobrov.entity.Answer;

import java.util.stream.Stream;

public class AnswerRepository extends BaseRepository<Answer> {

    @Override
    public Stream<Answer> find(Answer pattern) {
        return map.values()
                .stream()
                .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                .filter(u -> nullOrEquals(pattern.getQuestionId(), u.getQuestionId()))
                .filter(u -> nullOrEquals(pattern.getAnswerText(), u.getAnswerText()))
                .filter(u -> nullOrEquals(pattern.getAnswer_number(), u.getAnswer_number()));
    }
}
