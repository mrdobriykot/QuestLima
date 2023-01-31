package com.javarush.quest.marzhiievskyi.service;

import com.javarush.quest.marzhiievskyi.entity.GameSession;
import com.javarush.quest.marzhiievskyi.entity.GameState;
import com.javarush.quest.marzhiievskyi.entity.User;
import com.javarush.quest.marzhiievskyi.repository.Repository;
import com.javarush.quest.marzhiievskyi.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public enum UserService {
    USER_SERVICE;

    private final Repository<User> userRepository = new UserRepository();

    public void create(User user) {
        if (user.getGames() == null) {
            user.setGames(new ArrayList<>());
        }
        userRepository.create(user);
    }

    public User update(User user) {
        User userToUpdate = userRepository.get(user.getId());
        userToUpdate.setLogin(user.getLogin());
        userToUpdate.setPassword(user.getPassword());
        userRepository.update(userToUpdate);
        return userToUpdate;
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> get(Long id) {
        return Optional.ofNullable(userRepository.get(id));
    }

    public Optional<User> get(String login, String password) {
        User userToFind = User.builder()
                .login(login)
                .password(password)
                .build();
        return userRepository.find(userToFind).findAny();
    }

    public Collection<GameSession> getWins(Long userId) {
        return getGameSessionsByState(userId, GameState.WIN);
    }

    public Collection<GameSession> getLosses(Long userId) {
        return getGameSessionsByState(userId, GameState.LOST);
    }

    private Collection<GameSession> getGameSessionsByState(Long userId, GameState gameState) {
        Optional<User> optionalUser = get(userId);
        Collection<GameSession> gameSessions = new ArrayList<>();
        if (optionalUser.isPresent()) {

            Collection<GameSession> games = optionalUser.get().getGames();

            games.forEach(gameSession -> {
                if (gameSession.getGameState() == gameState) {
                    gameSessions.add(gameSession);
                }
            });
        }
        return gameSessions;
    }


    public boolean checkUserExist(User userToCreate) {
        String login = userToCreate.getLogin();

        User findPattern = User.builder()
                .login(login)
                .build();
        Optional<User> optionalUser = userRepository.find(findPattern).findAny();
        return optionalUser.isEmpty();
    }
}
