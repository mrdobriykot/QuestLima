package com.javarush.quest.khlopin.questlima.entity;

import com.javarush.quest.khlopin.questlima.excpetions.QuestException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository implements Repository {

    private final HashMap<Long, User> userMap = new HashMap<>();

    public static final AtomicLong id = new AtomicLong(4); // TODO По окончанию разработки заменить на 3 и убрать роль админа 1234
    public UserRepository() {
        userMap.put(1L,new User(1L,"Mihail","recd123",Role.ADMIN));
        userMap.put(2L, new User(2L,"Emma","saf423",Role.USER));
        userMap.put(3L, new User(3L, "Valik","fdsgh432",Role.GUEST));
        userMap.put(4L, new User(4L, "1234","1234", Role.ADMIN));
    }

    @Override
    public void create(String login, String password, Role role) {
        long key = id.incrementAndGet();

        for (Map.Entry<Long, User> longUserEntry : userMap.entrySet()) {
            if (longUserEntry.getValue().getUserName().equals(login)) {
                throw new QuestException();
            } else {
                userMap.put(key, new User(key, login, password, role));
            }
        }

    }

    @Override
    public User get(long id) {
        return userMap.get(id);
    }

    @Override
    public void delete(long id) {
        userMap.remove(id);
    }

    @Override
    public Collection<User> getAll() {
        return userMap.values();
    }

    @Override
    public void update(long idOfOlderUser, User user) {
        delete(idOfOlderUser);
        userMap.put(idOfOlderUser,user);
    }

    @Override
    public Optional<User> find(String login) {
        for (Map.Entry<Long, User> longUserEntry : userMap.entrySet()) {
            User user = longUserEntry.getValue();
            String userName = user.getUserName();
            if (userName.equals(login)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
