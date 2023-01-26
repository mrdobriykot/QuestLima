package com.javarush.quest.marzhiievskyi.repository;

import com.javarush.quest.marzhiievskyi.entity.Quest;

import java.util.stream.Stream;

public class QuestRepository extends BaseRepository<Quest> {


    @Override
    public Stream<Quest> find(Quest pattern) {
        return map.values().stream()
                .filter(quest -> nullOrEquals(pattern.getId(), quest.getId()))
                .filter(quest -> nullOrEquals(pattern.getName(), quest.getName()))
                .filter(quest -> nullOrEquals(pattern.getStartingText(), quest.getStartingText()))
                .filter(quest -> nullOrEquals(pattern.getStartQuestionId(), quest.getStartQuestionId()));
    }
}
