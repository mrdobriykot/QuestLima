package com.javarush.quest.ivanilov.utils.constants;

import com.javarush.quest.ivanilov.entities.game.Quest;
import com.javarush.quest.ivanilov.entities.users.User;

public class Messages {

    public static final String GENERIC_REASON = "Что-то пошло не так :(";
    public static final String USER_NOT_MODIFIED = "Не получилось внести изменения. Вероятно, данный логин уже существует.";
    public static final String USER_NOT_CREATED = "Пользователь не был создан.";
    public static final String NOT_AUTHORIZED = "Не получилось авторизовать пользователя.";
    public static final String INIT_PROBLEM = "Проблема при инициализации юзеров и/или квестов. Пройдите на главную, чтобы продолжить.";

    public String forbidden(String login) {
        return String.format("Access to user management is forbidden: user %s doesn't have necessary rights.", login);
    }

    public String userUpdated(User user) {
        return String.format("Пользователь %s успешно обновлён.", user.toString());
    }

    public Object userDeleted(User user) {
        return String.format("Пользователь %s успешно удалён.", user.toString());
    }

    public String userCreated(User user) {
        return String.format("Пользователь %s успешно создан.", user.toString());
    }

    public String questDeleted(Quest quest) {
        return String.format("Квест %s успешно удалён.", quest.toString());
    }
}
