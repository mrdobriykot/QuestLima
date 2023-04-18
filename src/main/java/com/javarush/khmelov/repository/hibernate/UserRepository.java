package com.javarush.khmelov.repository.hibernate;

import com.javarush.khmelov.entity.User;

public class UserRepository extends BaseRepository<User> {

    public UserRepository(SessionCreator sessionCreator) {
        super(sessionCreator, User.class);
    }
}
