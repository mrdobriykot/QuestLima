package com.javarush.quest.ivanilov.services;


import com.javarush.quest.ivanilov.entities.users.Roles;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.repositories.Repository;
import com.javarush.quest.ivanilov.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
@Slf4j
public enum UserService {

    USER_SERVICE;

    private final Repository<User> repo = new UserRepository();

    public Collection<User> getAll() {
        return repo.getAll();
    }

    public User create(User user) {
        return repo.create(user);
    }

    public User get(long id) {
        return repo.get(id);
    }

    public User update(User user) {
        return repo.update(user);
    }

    public void delete(User user) {
        repo.delete(user);
    }

    public Optional<User> find(String pattern) {
        return repo.find(pattern);
    }

    public User createOrModifyUser(String login, String password, Roles role, User userOrNull) {
        if (!attributesAreValid(login, password)) {
            return null;
        }

        User newUser;

        if (userOrNull == null) {
            newUser = getUser(login, password, role, null);
        } else {
            newUser = getUser(login, password, role, userOrNull);
        }

        if (newUser == null) {
            return null;
        }

        User actualUser;
        if (newUser.getId() == 0) {
            actualUser = USER_SERVICE.create(newUser);
        } else {
            actualUser = USER_SERVICE.update(newUser);
        }

        return actualUser;
    }

    private boolean attributesAreValid(String login, String password) {
        return !(StringUtils.isBlank(login) || StringUtils.isBlank(password));
    }

    private User getUser(String login, String password, Roles role, User userOrNull) {
        User newUser;

        if (userOrNull == null) {
            Optional<User> optionalUser = USER_SERVICE.find(login);
            if (optionalUser.isPresent()) {
                return null;
            }
            newUser = getUserBuild(login, password, role);
        } else {
            if (!userOrNull.getLogin().equals(login)) {
                Optional<User> optionalUser = USER_SERVICE.find(login);
                if (optionalUser.isPresent()) {
                    return null;
                }
            }
            newUser = getUserBuild(login, password, role, userOrNull);
        }
        return newUser;
    }

    private User getUserBuild(String login, String password, Roles role, User user) {
        User newUser = User.builder()
                .login(login)
                .password(password)
                .role(role)
                .gamesPlayed(user.getGamesPlayed())
                .currentGameId(user.getCurrentGameId())
                .build();
        newUser.setId(user.getId());
        return newUser;
    }

    private User getUserBuild(String login, String password, Roles role) {
        Roles actualRole = role != null ? role : Roles.USER;
        return User.builder()
                .login(login)
                .password(password)
                .role(actualRole)
                .gamesPlayed(new ArrayList<>())
                .currentGameId(0)
                .build();
    }
}
