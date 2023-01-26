package com.javarush.quest.marzhiievskyi.config;

import com.javarush.quest.marzhiievskyi.entity.User;
import com.javarush.quest.marzhiievskyi.service.GameSessionService;
import com.javarush.quest.marzhiievskyi.service.UserService;
import com.javarush.quest.marzhiievskyi.util.quests.test.DemoQuest;
import com.javarush.quest.marzhiievskyi.util.quests.test.TestQuest;

import java.util.ArrayList;

public class Init {


    final UserService userService = UserService.USER_SERVICE;
    final GameSessionService gameSessionService = GameSessionService.GAME_SESSION_SERVICE;

    public void initDefaultUsers() {
        userService.create(User.builder()
                .id(0L)
                .login("Amitamaru")
                .password("111111")
                .games(new ArrayList<>())
                .build());
        userService.create(User.builder()
                .id(0L)
                .login("Desmont")
                .password("123456")
                .games(new ArrayList<>())
                .build());
        userService.create(User.builder()
                .id(0L)
                .login("q")
                .password("q")
                .games(new ArrayList<>())
                .build());
    }

    public void initQuests() {
        gameSessionService.loadQuestToRepository(new DemoQuest());
        gameSessionService.loadQuestToRepository(new TestQuest());
    }

}
