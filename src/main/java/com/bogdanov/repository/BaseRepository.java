package com.bogdanov.repository;

import com.bogdanov.entity.AbstaractEntity;
import com.bogdanov.entity.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public abstract class BaseRepository<T extends AbstaractEntity> implements Repository<T> {

    protected final Map<Long, T> map = new HashMap<>();
    public  final AtomicLong id = new AtomicLong(0L);






    public Collection<T> getAll(){
        return map.values();
    }



    protected boolean nullOrEquals(Object patternFields, Object repoFields) {
        return patternFields == null || patternFields.equals(repoFields);
    }

    @Override
    public T get(long id) {
        return map.get(id);
    }

    @Override
    public void create(T entity) {
        entity.setId((id.incrementAndGet()));
        update(entity);


    }

    @Override
    public void update(T entity) {
        Long id1 = entity.getId();

        map.put(id1, entity);
    }

    @Override
    public void delete(T entity) {
        map.remove(entity.getId());
    }
}
