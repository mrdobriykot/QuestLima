package com.javarush.dobrov.repository;

import com.javarush.dobrov.entity.Question;

import java.util.stream.Stream;

public class QuestionRepository extends BaseRepository<Question> {


    @Override
    public Stream<Question> find(Question pattern) {
        return map.values()
                .stream()
                .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                .filter(u -> nullOrEquals(pattern.getQuestId(), u.getQuestId()))
                .filter(u -> nullOrEquals(pattern.getAnswers(), u.getAnswers()));
    }
}
