package com.javarush.mokropolov.repository;



import com.javarush.mokropolov.entity.Quest;

import java.util.stream.Stream;

public class QuestRepository extends BaseRepository<Quest> {


    @Override
    public Stream<Quest> find(Quest pattern) {
        return map.values()
                .stream()
                .filter(quest -> nullOrEquals(pattern.getId(), quest.getId()))
                .filter(quest -> nullOrEquals(pattern.getText(), quest.getText()))
                .filter(quest -> nullOrEquals(pattern.getName(), quest.getName()))
                .filter(quest -> nullOrEquals(pattern.getAuthorId(), quest.getAuthorId()))
                .filter(quest -> nullOrEquals(pattern.getStartQuestionId(), quest.getStartQuestionId()));

    }



}

