package com.javarush.quest.ivanilov.services;

import com.javarush.quest.ivanilov.entities.game.Event;
import com.javarush.quest.ivanilov.repositories.EventRepository;
import com.javarush.quest.ivanilov.repositories.Repository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum EventService {

    EVENT_SERVICE;

    private final Repository<Event> repo = new EventRepository();

    public Event create(Event event) {
        return repo.create(event);
    }

    public Event get(long id) {
        return repo.get(id);
    }

    public Event update(Event event) {
        return repo.update(event);
    }
}
