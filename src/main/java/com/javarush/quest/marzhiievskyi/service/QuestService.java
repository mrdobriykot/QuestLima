package com.javarush.quest.marzhiievskyi.service;

import com.javarush.quest.marzhiievskyi.entity.Quest;
import com.javarush.quest.marzhiievskyi.repository.QuestRepository;
import com.javarush.quest.marzhiievskyi.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public enum QuestService {
    QUEST_SERVICE;

    private final Repository<Quest> questRepository = new QuestRepository();

    public void create(Quest quest) {
        questRepository.create(quest);
    }

    public void update(Quest quest) {
        questRepository.update(quest);
    }

    public void delete(Quest quest) {
        questRepository.delete(quest);
    }

    public Quest get(Long questId) {
        return questRepository.get(questId);
    }

    public Optional<Quest> getByName(String questName) {
        Quest questToFind = Quest.builder()
                .name(questName)
                .build();
        return questRepository.find(questToFind).findAny();
    }
    public Collection<Quest> getAll() {
        return questRepository.getAll();
    }
}
