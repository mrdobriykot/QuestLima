package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.Quest;
import com.javarush.quest.khlopin.questlima.entity.Repository;
import com.javarush.quest.khlopin.questlima.entity.Role;
import com.javarush.quest.khlopin.questlima.entity.User;

import java.util.Collection;
import java.util.Optional;

public class QuestRepository implements Repository<Quest> {
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
    public void update(long idOfOlderQuest, Quest quest) {

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
