package com.javarush.khmelov.service;

import com.javarush.khmelov.dto.UserTo;
import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.mapping.Dto;
import com.javarush.khmelov.repository.impl.UserRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Optional<UserTo> create(String login, String password) {
        return create(null, login, password, Role.USER.toString());
    }

    public Optional<UserTo> create(String login, String password, String role) {
        return create(null, login, password, role);
    }

    public Optional<UserTo> create(Long id, String login, String password, String role) {
        User user = getUser(id, login, password, role);
        userRepository.create(user);
        return Optional.of(user).map(Dto.MAPPER::from);
    }

    public Optional<UserTo> update(Long id, String login, String password, String role) {
        User user = getUser(id, login, password, role);
        userRepository.update(user);
        return Optional.of(user).map(Dto.MAPPER::from);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }

    public Collection<UserTo> getAll() {
        return userRepository.getAll()
                .stream()
                .map(Dto.MAPPER::from)
                .toList();
    }

    public Optional<UserTo> get(long id) {
        return Optional.ofNullable(userRepository.get(id)).map(Dto.MAPPER::from);
    }

    public Optional<UserTo> get(String login, String password) {
        User patternUser = User
                .builder()
                .login(login)
                .password(password)
                .build();
        return userRepository
                .find(patternUser)
                .findAny()
                .map(Dto.MAPPER::from);
    }

    private User getUser(Long id, String login, String password, String role) {
        return User.builder()
                .id(id)
                .login(login)
                .password(password)
                .role(Role.valueOf(role))
                .build();
    }
}
