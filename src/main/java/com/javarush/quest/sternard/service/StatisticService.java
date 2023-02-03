package com.javarush.quest.sternard.service;

import com.javarush.quest.sternard.config.Settings;
import com.javarush.quest.sternard.entities.Quest;
import com.javarush.quest.sternard.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;

import static com.javarush.quest.sternard.util.Attribute.*;

public enum StatisticService {
    INSTANCE;

    private final UserService userService = UserService.INSTANCE;
    private final QuestService questService = QuestService.INSTANCE;
    private final long statMostPlayingUsersLimit = Settings.get().getStatMostPlayingUsersLimit();
    private final long statBestQuestLimit = Settings.get().getStatBestQuestLimit();

    public void getStatistic(HttpServletRequest req) {
        List<Quest> bestQuestsByRating = getBestQuests(Comparator.comparingDouble(Quest::getRating));
        List<Quest> bestQuestsByViews = getBestQuests(Comparator.comparingLong(Quest::getViews));

        req.setAttribute(PLAYERS_COUNT_ALL, getPlayersCountAll());
        req.setAttribute(QUESTS_COUNT_ALL, getQuestsCountAll());
        req.setAttribute(MOST_PLAYING_USERS, getMostPlayingUsers());
        req.setAttribute(BEST_QUESTS_BY_RATING, bestQuestsByRating);
        req.setAttribute(BEST_QUESTS_BY_VIEWS, bestQuestsByViews);
    }

    private int getQuestsCountAll() {
        return questService.getAllEntities().size();
    }

    private int getPlayersCountAll() {
        return userService.getAllEntities().size();
    }

    private List<User> getMostPlayingUsers() {
        return userService.getAllEntities()
                .stream()
                .sorted(Comparator.comparingLong(User::getPlayedQuestsCount).reversed())
                .limit(statMostPlayingUsersLimit)
                .toList();
    }

    private List<Quest> getBestQuests(Comparator<Quest> comparing) {
        return questService.getAllEntities()
                .stream()
                .sorted(comparing.reversed())
                .limit(statBestQuestLimit)
                .toList();
    }

}