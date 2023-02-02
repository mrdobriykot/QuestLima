package com.javarush.quest.uzienko.questlima.service;

import java.util.Collection;
import java.util.Optional;

public interface CrudService<T> {
    void create(T entity);

    void update(T entity);

    void delete(T entity);

    Collection<T> getAll();

    Optional<T> get(Long id);
}
