package com.javarush.quest.sternard.service;

import com.javarush.quest.sternard.entities.AbstractEntity;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface Service<T extends AbstractEntity> {
    Optional<T> create(Map<String, String> checkedParamsMap);

    boolean update(T entity, Map<String, String> checkedParamsMap);

    void delete(long id);

    Map<Long, T> getEntitiesMap();

    Collection<T> getAllEntities();
}
