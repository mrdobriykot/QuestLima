package com.javarush.quest.ivanilov.services;


import com.javarush.quest.ivanilov.constants.Attributes;
import com.javarush.quest.ivanilov.constants.Messages;
import com.javarush.quest.ivanilov.constants.Targets;
import com.javarush.quest.ivanilov.entities.users.Operation;
import com.javarush.quest.ivanilov.entities.users.Roles;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.repositories.Repository;
import com.javarush.quest.ivanilov.repositories.UserRepository;
import com.javarush.quest.ivanilov.utils.Navigator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
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

    public User createOrModifyUser(HttpServletRequest req, HttpServletResponse resp, Map<String, String> attributes, Operation operation) throws ServletException, IOException {
        String login = attributes.getOrDefault(Attributes.LOGIN, null);
        String password = attributes.getOrDefault(Attributes.PASSWORD, null);

        if (!attributesAreValid(req, resp, login, password)) {
            return null;
        }

        User newUser = getUser(req, resp, attributes, operation, login);
        if (newUser == null) {
            return null;
        }

        User actualUser;
        if (operation.equals(Operation.CREATE)) {
            actualUser = USER_SERVICE.create(newUser);
        } else {
            actualUser = USER_SERVICE.update(newUser);
        }

        return actualUser;
    }

    private boolean attributesAreValid(HttpServletRequest req, HttpServletResponse resp, String login, String password) throws ServletException, IOException {
        if (StringUtils.isBlank(login)) {
            req.setAttribute(Attributes.MESSAGE, Messages.NO_LOGIN);
            Navigator.dispatch(req, resp, Targets.ERROR_MESSAGE_JSP);
            return false;
        }

        if (StringUtils.isBlank(password)) {
            req.setAttribute(Attributes.MESSAGE, Messages.NO_PASSWORD);
            Navigator.dispatch(req, resp, Targets.ERROR_MESSAGE_JSP);
            return false;
        }
        return true;
    }

    private User getUser(HttpServletRequest req, HttpServletResponse resp, Map<String, String> attributes, Operation operation, String login) throws ServletException, IOException {
        User newUser;
        switch (operation) {
            case CREATE -> {
                Optional<User> optionalUser = USER_SERVICE.find(login);
                if (optionalUser.isPresent()) {
                    req.setAttribute(Attributes.MESSAGE, new Messages().userAlreadyExists(optionalUser.get().getLogin()));
                    Navigator.dispatch(req, resp, Targets.ERROR_MESSAGE_JSP);
                    return null;
                }
                newUser = getUserBuild(attributes);
            }
            case UPDATE -> {
                User currUser = (User) req.getSession().getAttribute(Attributes.USER);
                if (!currUser.getLogin().equals(login)) {
                    Optional<User> optionalUser = USER_SERVICE.find(login);
                    if (optionalUser.isPresent()) {
                        req.setAttribute(Attributes.MESSAGE, new Messages().userAlreadyExists(optionalUser.get().getLogin()));
                        Navigator.dispatch(req, resp, Targets.ERROR_MESSAGE_JSP);
                        return null;
                    }
                }
                newUser = getUserBuild(attributes, currUser);
                newUser.setId(currUser.getId());
            }
            default -> throw new UnsupportedOperationException();
        }
        return newUser;
    }

    private User getUserBuild(Map<String, String> attributes, User user) {
        String login = attributes.getOrDefault(Attributes.LOGIN, null);
        String password = attributes.getOrDefault(Attributes.PASSWORD, null);
        Roles role = Roles.valueOf(attributes.getOrDefault(Attributes.ROLE, null));
        return User.builder()
                .login(login)
                .password(password)
                .role(role)
                .gamesPlayed(user.getGamesPlayed())
                .currentGameId(user.getCurrentGameId())
                .build();
    }

    private User getUserBuild(Map<String, String> attributes) {
        String login = attributes.getOrDefault(Attributes.LOGIN, null);
        String password = attributes.getOrDefault(Attributes.PASSWORD, null);
        return User.builder()
                .login(login)
                .password(password)
                .role(Roles.USER)
                .gamesPlayed(new ArrayList<>())
                .currentGameId(0)
                .build();
    }
}
