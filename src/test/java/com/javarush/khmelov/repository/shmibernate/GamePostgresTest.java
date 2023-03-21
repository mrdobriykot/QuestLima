package com.javarush.khmelov.repository.shmibernate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.javarush.khmelov.entity.Game;
import com.javarush.khmelov.entity.GameState;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.repository.Repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GamePostgresTest {
    private final Repository<Game> gameRepository = PostgresRepository.get(Game.class);
    private final Repository<User> userRepository = PostgresRepository.get(User.class);

    @Test
    @DisplayName("When create+update+delete tempGame then no Exception")
    void createUpdateDelete() {
        User user = userRepository.get(1L);
        Game tempGame = Game.builder()
                .gameState(GameState.PLAY)
                .userId(user.getId())
                .questId(1L)
                .build();
        gameRepository.create(tempGame);
        System.out.println("CREATE " + tempGame);

        tempGame.setGameState(GameState.WIN);
        gameRepository.update(tempGame);
        System.out.println("UPDATE " + tempGame);

        gameRepository.delete(tempGame);
        System.out.println("DELETE " + tempGame);
        assertTrue(tempGame.getId() > 0);
    }
}