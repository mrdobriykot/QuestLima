package com.javarush.quest.ivanilov.repositories;

import com.javarush.quest.ivanilov.entities.AbstractEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public abstract class BaseRepository<T extends AbstractEntity> implements Repository<T> {
    protected final Map<Long, T> map = new HashMap<>();
    public final AtomicLong id = new AtomicLong(0L);

    @Override
    public Collection<T> getAll() {
        return map.values();
    }

    @Override
    public T get(long id) {
        return map.get(id);
    }

    @Override
    public T create(T entity) {
        entity.setId(id.incrementAndGet());
        map.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void delete(T entity) {
        map.remove(entity.getId());
    }

    @Override
    public T update(T entity) {
        return map.put(entity.getId(), entity);
    }
}
