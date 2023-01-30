package com.javarush.quest.ivanilov.utils;

import com.javarush.quest.ivanilov.entities.game.Game;
import com.javarush.quest.ivanilov.entities.game.GameStatus;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.entities.users.UserToSend;
import com.javarush.quest.ivanilov.services.GameService;
import com.javarush.quest.ivanilov.services.QuestService;
import com.javarush.quest.ivanilov.services.UserService;
import com.javarush.quest.ivanilov.utils.constants.Strings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Users {
    private static final GameService gameService = GameService.GAME_SERVICE;
    private static final QuestService questService = QuestService.QUEST_SERVICE;
    private static final UserService userService = UserService.USER_SERVICE;


    public static Collection<UserToSend> extractUsersToSend(Collection<User> users) {
        Collection<UserToSend> usersToSend = new ArrayList<>();

        for (User user : users) {
            Game currentGame = gameService.get(user.getCurrentGameId());
            long questId;
            String questName = Strings.NOT_PLAYING;

            if (currentGame != null) {
                questId = currentGame.getQuestId();
                questName = questService.get(questId).getName();
            }

            UserToSend userToSend = UserToSend.builder()
                    .id(user.getId())
                    .login(user.getLogin())
                    .password(user.getPassword())
                    .role(user.getRole())
                    .gamesPlayed(user.getGamesPlayed().size())
                    .gamesWon(calculateWins(user))
                    .currentGameName(questName)
                    .build();

            usersToSend.add(userToSend);
        }
        return usersToSend;
    }

    public static long calculateWins(User user) {
        List<Long> gamesPlayed = userService.get(user.getId()).getGamesPlayed();
        return gamesPlayed.stream().filter(o -> gameService.get(o).getStatus().equals(GameStatus.WON)).count();
    }
}
