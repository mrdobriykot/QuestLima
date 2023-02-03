package com.javarush.quest.sternard.repository;

import com.javarush.quest.sternard.entities.User;
import com.javarush.quest.sternard.repository.mysql.UserRepository;

public enum ChooserUsersRepository {
    MAP_REPOS(new com.javarush.quest.sternard.repository.local.UserRepository()),
    JSON_REPOS(new com.javarush.quest.sternard.repository.json.UserRepository()),
    MYSQL_REPOS(new UserRepository()),
    HIBERNATE_REPOS(new com.javarush.quest.sternard.repository.hibernate.UserRepository());
    private final Repository<User> repository;

    ChooserUsersRepository(Repository<User> repository) {
        this.repository = repository;
    }

    public Repository<User> getRepository() {
        return repository;
    }
}