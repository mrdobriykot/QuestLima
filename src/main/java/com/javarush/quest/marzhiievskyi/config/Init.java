package com.javarush.quest.marzhiievskyi.config;

import com.javarush.quest.marzhiievskyi.entity.User;
import com.javarush.quest.marzhiievskyi.service.GameSessionService;
import com.javarush.quest.marzhiievskyi.service.UserService;

public class Init {


    final UserService userService = UserService.USER_SERVICE;
    final GameSessionService gameSessionService = GameSessionService.GAME_SESSION_SERVICE;

    public void initDefaultUsers() {
        userService.create(User.builder()
                .id(0L)
                .login("Amitamaru")
                .password("111111")
                .build());
        userService.create(User.builder()
                .id(0L)
                .login("Desmont")
                .password("123456")
                .build());
        userService.create(User.builder()
                .id(0L)
                .login("q")
                .password("q")
                .build());
    }

    public void initQuestsFromRepository() {
        gameSessionService.createQuest();
    }

}
