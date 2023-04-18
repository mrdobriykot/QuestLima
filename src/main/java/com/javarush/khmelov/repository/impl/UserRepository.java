package com.javarush.khmelov.repository.impl;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.repository.BaseRepository;
import com.javarush.khmelov.config.SessionCreator;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Stream;

public class UserRepository extends BaseRepository<User> {

    public UserRepository(SessionCreator sessionCreator) {
        super(sessionCreator, User.class);
    }

    @Transactional
    @Override
    public User get(long id) {
        return super.get(id);
    }

    @Transactional
    @Override
    public void create(User entity) {
        super.create(entity);
    }

    @Transactional
    @Override
    public Collection<User> getAll() {
        return super.getAll();
    }

    @Transactional
    @Override
    public Stream<User> find(User pattern) {
        return super.find(pattern);
    }

    @Transactional
    @Override
    public void update(User entity) {
        super.update(entity);
    }

    @Transactional
    @Override
    public void delete(User entity) {
        super.delete(entity);
    }
}
