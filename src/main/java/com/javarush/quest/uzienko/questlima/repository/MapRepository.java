package com.javarush.quest.uzienko.questlima.repository;

import com.javarush.quest.uzienko.questlima.entity.Entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public abstract class MapRepository<T extends Entity> implements Repository<T> {
    private final Map<Long, T> map = new HashMap<>();

    public final AtomicLong id = new AtomicLong(0L);

    @Override
    public Collection<T> getAll() {
        return map.values();
    }


    @Override
    public Optional<T> get(long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void create(T entity) {
        entity.setId(id.incrementAndGet());
        update(entity);
    }

    @Override
    public void update(T entity) {
        map.put(entity.getId(), entity);
    }

    @Override
    public void delete(T entity) {
        map.remove(entity.getId());
    }

    protected boolean nullOrEquals(Object patternField, Object repoField) {
        return patternField == null || patternField.equals(repoField);
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }
}
