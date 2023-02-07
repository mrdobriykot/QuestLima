package com.javarush.quest.uzienko.questlima.repository;

import com.javarush.quest.uzienko.questlima.entity.Question;

import java.util.stream.Stream;

public class QuestionMapRepository extends MapRepository<Question> {
    @Override
    public Stream<Question> find(Question pattern) {
        return getAll().stream()
                .filter(e -> nullOrEquals(pattern.getId(), e.getId()))
                .filter(e -> nullOrEquals(pattern.getQuestId(), e.getQuestId()))
                .filter(e -> nullOrEquals(pattern.getText(), e.getText()))
                .filter(e -> nullOrEquals(pattern.getQuestState(), e.getQuestState()))
                ;
    }
}
