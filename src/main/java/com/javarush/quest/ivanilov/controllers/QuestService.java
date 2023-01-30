package com.javarush.quest.ivanilov.controllers;


import com.javarush.quest.ivanilov.entities.game.Quest;
import com.javarush.quest.ivanilov.repositories.QuestRepository;
import com.javarush.quest.ivanilov.repositories.Repository;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Optional;
@Slf4j
public enum QuestService {

    QUEST_SERVICE;

    private final Repository<Quest> repo = new QuestRepository();

    public Collection<Quest> getAll() {
        return repo.getAll();
    }

    public Quest create(Quest quest) {
        return repo.create(quest);
    }

    public Quest get(long id) {
        return repo.get(id);
    }

    public Quest update(Quest quest) {
        return repo.update(quest);
    }

    public void delete(Quest quest) {
        repo.delete(quest);
    }

    public Optional<Quest> find(String pattern) {
        return repo.find(pattern);
    }
}
