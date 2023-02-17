package com.javarush.quest.sternard.repository;

import com.javarush.quest.sternard.entities.Quest;
import com.javarush.quest.sternard.repository.mysql.QuestRepository;

public enum ChooserQuestsRepository {
    MAP_REPOS(new com.javarush.quest.sternard.repository.local.QuestRepository()),
    JSON_REPOS(new com.javarush.quest.sternard.repository.json.QuestRepository()),
    MYSQL_REPOS(new QuestRepository());
    private final Repository<Quest> repository;

    ChooserQuestsRepository(Repository<Quest> repository) {
        this.repository = repository;
    }

    public Repository<Quest> getRepository() {
        return repository;
    }
}