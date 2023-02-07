package com.javarush.quest.ivanilov.services;


import com.javarush.quest.ivanilov.entities.game.Quest;
import com.javarush.quest.ivanilov.repositories.QuestRepository;
import com.javarush.quest.ivanilov.repositories.Repository;

import java.util.Collection;

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

    public void delete(long questId) {
        Quest quest = QUEST_SERVICE.get(questId);
        repo.delete(quest);
    }
}
