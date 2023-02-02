package com.javarush.quest.uzienko.questlima.repository;

import com.javarush.quest.uzienko.questlima.entity.Answer;

import java.util.stream.Stream;

public class AnswerMapRepository extends MapRepository<Answer> {
    @Override
    public Stream<Answer> find(Answer pattern) {
        return getAll().stream()
                .filter(e -> nullOrEquals(pattern.getId(), e.getId()))
                .filter(e -> nullOrEquals(pattern.getQuestionId(), e.getQuestionId()))
                .filter(e -> nullOrEquals(pattern.getText(), e.getText()))
                .filter(e -> nullOrEquals(pattern.getNextQuestionId(), e.getNextQuestionId()))
                ;
    }
}
