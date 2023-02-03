package com.javarush.quest.sternard.repository;

import com.javarush.quest.sternard.entities.AbstractEntity;

import java.util.Collection;
import java.util.Map;

public interface Repository<T extends AbstractEntity> {
    void create(T entity);

    void update(T entity);

    void delete(long id);

    Collection<T> getAllEntities();

    Map<Long, T> getEntitiesMap();
}
