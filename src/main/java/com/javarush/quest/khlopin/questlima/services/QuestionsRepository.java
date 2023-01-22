package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.Question;
import com.javarush.quest.khlopin.questlima.entity.Repository;
import com.javarush.quest.khlopin.questlima.entity.Role;
import com.javarush.quest.khlopin.questlima.entity.User;

import java.util.Collection;
import java.util.Optional;

public class QuestionsRepository implements Repository<Question> {

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
    public void update(long idOfOlderQuestion, Question question) {

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
