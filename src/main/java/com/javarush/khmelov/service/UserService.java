package com.javarush.khmelov.service;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.repository.impl.UserRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void create(User user) {
        userRepository.create(user);
    }

    @Transactional
    public void update(User user) {
        userRepository.update(user);
    }

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Transactional
    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    @Transactional
    public Optional<User> get(long id) {
        return Optional.ofNullable(userRepository.get(id));
    }

    @Transactional
    public Optional<User> get(String login, String password) {
        User patternUser = User
                .builder()
                .login(login)
                .password(password)
                .build();
        return userRepository.find(patternUser).findAny();
    }
}
