package com.javarush.quest.ivanilov.repositories;

import com.javarush.quest.ivanilov.entities.game.Quest;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Optional;

public class QuestRepository extends BaseRepository<Quest>{

    @Override
    public Optional<Quest> find(String pattern) {
        throw new NotImplementedException();
    }
}
