package com.javarush.khmelov.repository.impl;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.repository.BaseRepository;
import com.javarush.khmelov.repository.helper.SessionCreator;

import javax.transaction.Transactional;

public class UserRepository extends BaseRepository<User> {

    public UserRepository(SessionCreator sessionCreator) {
        super(sessionCreator, User.class);
    }

    @Transactional
    @Override
    public User get(long id) {
        return super.get(id);
    }
}
