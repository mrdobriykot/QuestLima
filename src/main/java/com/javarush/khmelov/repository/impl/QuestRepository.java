package com.javarush.khmelov.repository.impl;

import com.javarush.khmelov.config.SessionCreator;
import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.repository.BaseRepository;

public class QuestRepository extends BaseRepository<Quest> {

    public QuestRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Quest.class);
    }
}
