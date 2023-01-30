package com.javarush.quest.ivanilov.repositories;

import com.javarush.quest.ivanilov.entities.game.Event;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Optional;

public class EventRepository extends BaseRepository<Event> {
    @Override
    public Optional<Event> find(String pattern) {
        throw new NotImplementedException();
    }
}
