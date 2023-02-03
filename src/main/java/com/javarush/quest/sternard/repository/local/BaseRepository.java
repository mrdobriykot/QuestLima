package com.javarush.quest.sternard.repository.local;

import com.javarush.quest.sternard.entities.AbstractEntity;
import com.javarush.quest.sternard.exception.HandlerExceptions;
import com.javarush.quest.sternard.repository.Repository;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static com.javarush.quest.sternard.util.message.LoggerMessages.USER_WITH_THIS_ID_IS_EXIST;

@Slf4j
public abstract class BaseRepository<T extends AbstractEntity> implements Repository<T> {
    protected final Map<Long, T> map = new HashMap<>();
    protected final AtomicLong id = new AtomicLong(System.currentTimeMillis());

    public void create(T entity) {
        long getId = id.incrementAndGet();
        entity.setId(getId);
        if (!map.containsKey(getId))
            this.map.put(entity.getId(), entity);
        else {
            log.error(USER_WITH_THIS_ID_IS_EXIST, getId);
            throw new HandlerExceptions(USER_WITH_THIS_ID_IS_EXIST + getId);
        }
    }

    public void delete(long id) {
        this.map.remove(id);
    }

    public void update(T entity) {
        map.put(entity.getId(), entity);
    }

    public Collection<T> getAllEntities() {
        return this.map.values();
    }

    public Map<Long, T> getEntitiesMap() {
        return map;
    }
}