package com.javarush.quest.sternard.service;

import com.javarush.quest.sternard.config.Settings;
import com.javarush.quest.sternard.entities.Role;
import com.javarush.quest.sternard.entities.User;
import com.javarush.quest.sternard.util.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static com.javarush.quest.sternard.listener.Listener.userRepository;
import static com.javarush.quest.sternard.util.Parameter.*;

@Slf4j
public enum UserService implements Service<User> {
    INSTANCE;

    @Override
    public Optional<User> create(Map<String, String> checkedParamsMap) {
        String login = checkedParamsMap.get(LOGIN);
        String password = checkedParamsMap.get(PASSWORD_REG);
        String role = checkedParamsMap.get(ROLE);
        String image = checkedParamsMap.get(IMAGE);

        long isSameLoginExist = getLoginExistNowCount(login);
        if (isSameLoginExist == 0) {
            String encodedPassword = getHash(password);
            if (!isNotNullAndNotEmpty(image)) image = Settings.get().getDefaultAvatarImage();
            try {
                User newUser = getNewUser(login, role, image, encodedPassword);
                userRepository.create(newUser);
                return Optional.of(newUser);
            } catch (IllegalArgumentException e) {
                log.error(e.getMessage());
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean update(User user, Map<String, String> checkedParamsMap) {
        String login = checkedParamsMap.get(Parameter.LOGIN);
        String password = checkedParamsMap.get(Parameter.PASSWORD);
        String role = checkedParamsMap.get(Parameter.ROLE);
        String image = checkedParamsMap.get(Parameter.IMAGE);
        int countExistSameUsers = 2;
        if (this.getLoginExistNowCount(login) < countExistSameUsers) {
            for (User usr : userRepository.getAllEntities()) {
                if (usr.getId() == user.getId()) {
                    if (!setLogin(login, usr)) return false;
                    if (!setRole(role, usr)) return false;
                    setNewPassword(password, usr);
                    setImage(image, usr);
                    userRepository.update(usr);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }

    @Override
    public Map<Long, User> getEntitiesMap() {
        return userRepository.getEntitiesMap();
    }

    @Override
    public Collection<User> getAllEntities() {
        return userRepository.getAllEntities();
    }

    private boolean setRole(String role, User usr) {
        if (isNotNullAndNotEmpty(role)) {
            try {
                usr.setRole(Role.valueOf(role));
            } catch (IllegalArgumentException e) {
                log.error(e.getMessage());
                return false;
            }
        }
        return true;
    }

    private void setNewPassword(String newPassword, User usr) {
        if (isNotNullAndNotEmpty(newPassword)) {
            usr.setPassword(getHash(newPassword));
        }
    }

    private void setImage(String image, User usr) {
        if (isNotNullAndNotEmpty(image)) {
            usr.setProfileImage(image);
        }
    }

    private boolean setLogin(String login, User usr) {
        if (isNotNullAndNotEmpty(login)) {
            usr.setLogin(login);
        } else {
            return false;
        }
        return true;
    }

    public Optional<User> findUserById(long id) {
        return userRepository.getAllEntities()
                .stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    private long getLoginExistNowCount(String login) {
        return getAllEntities()
                .stream()
                .filter(u -> u.getLogin().equals(login))
                .count();
    }

    public Optional<User> findUserByLoginPassword(String login, String password) {
        return userRepository.getAllEntities()
                .stream()
                .filter(u -> (u.getLogin().equals(login) && u.getPassword().equals(getHash(password))))
                .findFirst();
    }

    private String getHash(String password) {  // need salt...
        return DigestUtils.sha256Hex(
                Base64.encodeBase64(
                        password.getBytes()
                ));
    }

    private boolean isNotNullAndNotEmpty(String parameter) {
        return parameter != null && !parameter.trim().isEmpty();
    }

    public void updatePlayedQuestsCount(User user) {
        int playedQuestsCountIncrement = user.getPlayedQuestsCount() + 1;
        user.setPlayedQuestsCount(playedQuestsCountIncrement);
        userRepository.update(user);
    }

    private User getNewUser(String login, String role, String image, String encodedPassword) {
        return User.builder()
                .login(login)
                .password(encodedPassword)
                .role(Role.valueOf(role))
                .profileImage(image)
                .build();
    }

}