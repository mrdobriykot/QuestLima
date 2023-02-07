package com.javarush.quest.uzienko.questlima.repository;

import com.javarush.quest.uzienko.questlima.entity.Quest;

import java.util.stream.Stream;

public class QuestMapRepository extends MapRepository<Quest> {
    @Override
    public Stream<Quest> find(Quest pattern) {
        return getAll().stream()
                .filter(e -> nullOrEquals(pattern.getId(), e.getId()))
                .filter(e -> nullOrEquals(pattern.getName(), e.getName()))
                .filter(e -> nullOrEquals(pattern.getStartQuestionId(), e.getStartQuestionId()))
                .filter(e -> nullOrEquals(pattern.getDescription(), e.getDescription()))
                ;
    }
}
