package com.javarush.quest.marzhiievskyi.config;

import com.javarush.quest.marzhiievskyi.entity.User;
import com.javarush.quest.marzhiievskyi.service.GameSessionService;
import com.javarush.quest.marzhiievskyi.service.UserService;
import com.javarush.quest.marzhiievskyi.util.NameConstants;
import com.javarush.quest.marzhiievskyi.util.quests.forest.EscapeFromForestQuest;
import com.javarush.quest.marzhiievskyi.util.quests.test.DemoQuest;

public class Init {


    final UserService userService = UserService.USER_SERVICE;
    final GameSessionService gameSessionService = GameSessionService.GAME_SESSION_SERVICE;

    public void initDefaultUsers() {

        userService.create(User.builder()
                .id(0L)
                .login(NameConstants.AMITAMARU)
                .password(NameConstants.PASSWORD)
                .build());
        userService.create(User.builder()
                .id(0L)
                .login(NameConstants.DESMONT)
                .password(NameConstants.PASSWORD_2)
                .build());
        userService.create(User.builder()
                .id(0L)
                .login(NameConstants.Q)
                .password(NameConstants.PASSWORD_3)
                .build());
    }

    public void initQuests() {
        gameSessionService.create(new EscapeFromForestQuest());
        gameSessionService.create(new DemoQuest());
    }

}
