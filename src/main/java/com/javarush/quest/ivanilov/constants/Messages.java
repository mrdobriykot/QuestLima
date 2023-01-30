package com.javarush.quest.ivanilov.constants;

import com.javarush.quest.ivanilov.entities.users.User;

public class Messages {

    public static final String NO_PASSWORD = "Пароль не указан. Пожалуйста, заполните пароль.";
    public static final String NO_LOGIN = "Логин не указан. Пожалуйста, укажите логин.";
    public static final String GENERIC_REASON = "Что-то пошло не так :(";
    public static final String LOGOUT_SUCCESS = "Вы успешно разлогинились.";

    public String userAlreadyExists(String login) {
        return String.format("Не получилось авторизовать пользователя. Причина: пользователь %s уже существует. Попробуйте другой логин.", login);
    }

    public String forbidden(String login) {
        return String.format("Access to user management is forbidden: user %s doesn't have necessary rights.", login);
    }

    public String userUpdatedSuccessfully(User user) {
        return String.format("Пользователь %s успешно обновлён.", user.toString());
    }

    public Object userDeleted(User user) {
        return String.format("Пользователь %s успешно удалён.", user.toString());
    }

    public String userCreated(User user) {
        return String.format("Пользователь %s успешно создан.", user.toString());
    }
}
