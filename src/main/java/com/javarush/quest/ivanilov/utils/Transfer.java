package com.javarush.quest.ivanilov.utils;

import com.javarush.quest.ivanilov.entities.game.Game;
import com.javarush.quest.ivanilov.entities.game.GameStatus;
import com.javarush.quest.ivanilov.entities.game.Quest;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.GameService;
import com.javarush.quest.ivanilov.services.QuestService;
import com.javarush.quest.ivanilov.services.UserService;
import com.javarush.quest.ivanilov.utils.constants.Strings;
import com.javarush.quest.ivanilov.utils.transfers.QuestDto;
import com.javarush.quest.ivanilov.utils.transfers.UserDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Transfer {
    private final GameService gameService = GameService.GAME_SERVICE;
    private final QuestService questService = QuestService.QUEST_SERVICE;
    private final UserService userService = UserService.USER_SERVICE;


    public Collection<UserDto> extractUsersToSend(Collection<User> users) {
        Collection<UserDto> usersToSend = new ArrayList<>();

        for (User user : users) {
            usersToSend.add(getUserDto(user));
        }

        return usersToSend;
    }

    public Collection<QuestDto> extractQuestsToSend(Collection<Quest> quests) {
        Collection<QuestDto> questsToSend = new ArrayList<>();

        for (Quest quest : quests) {
            questsToSend.add(getQuestDto(quest));
        }

        return questsToSend;
    }

    private QuestDto getQuestDto(Quest quest) {
        long authorId;
        String authorName;

        if (quest.getAuthorId() != 0) {
            authorId = quest.getAuthorId();
            authorName = userService.get(authorId).getLogin();
        } else {
            authorName = Strings.DEFAULT;
        }

        return QuestDto.builder()
                .questId(quest.getId())
                .name(quest.getName())
                .authorName(authorName)
                .build();
    }

    private UserDto getUserDto(User user) {
        Game currentGame = gameService.get(user.getCurrentGameId());
        long questId;
        String questName = Strings.NOT_PLAYING;

        if (currentGame != null) {
            questId = currentGame.getQuestId();
            questName = questService.get(questId).getName();
        }

        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .role(user.getRole())
                .gamesPlayed(user.getGamesPlayed().size())
                .gamesWon(calculateWins(user))
                .currentGameName(questName)
                .build();
    }

    private long calculateWins(User user) {
        List<Long> gamesPlayed = userService.get(user.getId()).getGamesPlayed();
        return gamesPlayed.stream().filter(o -> gameService.get(o).getStatus().equals(GameStatus.WON)).count();
    }
}
