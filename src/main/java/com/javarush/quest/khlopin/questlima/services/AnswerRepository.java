package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.Answer;
import com.javarush.quest.khlopin.questlima.entity.Repository;
import com.javarush.quest.khlopin.questlima.entity.Role;
import com.javarush.quest.khlopin.questlima.entity.User;

import java.util.Collection;
import java.util.Optional;

public class AnswerRepository implements Repository<Answer> {

    @Override
    public void create(String login, String password, Role role) {

    }

    @Override
    public User get(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update(long idOfOlderUser, Answer user) {

    }

    @Override
    public Collection<User> getAll() {
        return null;
    }

    @Override
    public Optional<User> find(String login) {
        return Optional.empty();
    }
}
