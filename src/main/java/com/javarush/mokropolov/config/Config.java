package com.javarush.mokropolov.config;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import com.javarush.mokropolov.entity.Role;
import com.javarush.mokropolov.entity.User;
import com.javarush.mokropolov.service.QuestService;
import com.javarush.mokropolov.service.UserService;

public class Config {

    public static void fillEmptyRepository() {
        UserService userService = Winter.getBean(UserService.class);
        if (userService.get(1L).isEmpty()) {
            User admin = new User(-1L, "admin", "admin", Role.ADMIN);
            User user = new User(-1L, "user", "user", Role.USER);
            User guest = new User(-1L, "guest", "guest", Role.GUEST);

            userService.create(admin);
            userService.create(user);
            userService.create(guest);

            QuestService questService = Winter.getBean(QuestService.class);
            Long adminId = admin.getId();

            questService.create(
                    "Играем в неопознанный летающий объект (обязательный квест)",
                    """
                            1: Ты потерял память. Принять вызов НЛО?
                            2<  Принять вызов
                            91< Отклонить вызов
                                                    
                            2: Ты принял вызов. Подняться на мостик к капитану?
                            92< Отказаться подниматься на мостик
                            3< Подняться на мостик
                                                    
                            3: Ты поднялся на мостик. Ты кто?
                            93< Солгать о себе
                            99< Рассказать правду
                                                    
                            91- Ты отклонил вызов. Поражение.
                            92- Ты не пошел на переговоры. Поражение.
                            93- Твою ложь разоблачили. Поражение.

                            99+ Вы выиграли
                            """,
                    adminId
            );
        }
    }

    public static final Path WEB_INF = Paths.get(URI.create(
                    Objects.requireNonNull(
                            Config.class.getResource("/")
                    ).toString()))
            .getParent();
}