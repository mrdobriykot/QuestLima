package com.javarush.quest.marzhiievskyi.repository;

import com.javarush.quest.marzhiievskyi.entity.Quest;
import com.javarush.quest.marzhiievskyi.resource.TextForQuest;

import java.util.stream.Stream;

public class QuestRepository extends BaseRepository<Quest> {

    public QuestRepository() {
        create(Quest.builder()
                .id(-1L).name(TextForQuest.NAME_QUEST_ESQ)
                .startingText(TextForQuest.STARTING_TEXT_QUEST)
                .build());
        create(Quest.builder()
                .id(-1L).name("abra cadabra")
                .startingText(TextForQuest.STARTING_TEXT_QUEST)
                .build());//TODO initialize where should be?
    }

    @Override
    public Stream<Quest> find(Quest pattern) {
        return map.values().stream()
                .filter(quest -> nullOrEquals(pattern.getId(), quest.getId()))
                .filter(quest -> nullOrEquals(pattern.getName(), quest.getName()))
                .filter(quest -> nullOrEquals(pattern.getStartingText(), quest.getStartingText()))
                .filter(quest -> nullOrEquals(pattern.getStartQuestionId(), quest.getStartQuestionId()));
    }
}
