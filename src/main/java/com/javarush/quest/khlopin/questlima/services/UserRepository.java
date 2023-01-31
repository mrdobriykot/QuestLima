package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.entity.user.Role;
import com.javarush.quest.khlopin.questlima.entity.user.User;
import com.javarush.quest.khlopin.questlima.excpetions.QuestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository implements Repository<User> {

    private final HashMap<Long, User> userMap = new HashMap<>();

    private static final AtomicLong id = new AtomicLong(0); // TODO По окончанию разработки убрать роль админа 1234

    private static final Logger log = LogManager.getLogger(UserRepository.class);

    public UserRepository() {

        create("Mihail", "recd123", Role.ADMIN);
        create("Emma", "saf423", Role.USER);
        create("Valik", "fdsgh432", Role.GUEST);
        create("1234", "1234", Role.ADMIN);
        log.trace("User repository was uploaded");
    }

    public void create(String login, String password, Role role) {
        long key = id.incrementAndGet();
        Optional<User> user = find(login);
        if (user.isEmpty()) {
            User userForMap = new User(key, login, password, role, new ArrayList<>());
            userMap.put(key, userForMap);
            log.info("Пользователь " + userForMap + " был создан");
        } else {
            throw new QuestException("Пользователь с таким именем уже создан");
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
        userMap.put(idOfOlderUser, user);
    }

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
