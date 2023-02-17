package com.javarush.quest.sternard.util.message;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessages {
    public static final String USER_BAD_PARAMS = "Проверьте параметры в форме";
    public static final String USER_BAD_PARAMS_OR_LOGIN_BUSY = "Проверьте параметры в форме или этот логин уже занят";
    public static final String USER_EXIST = "Пользователь с таким логином уже есть";
    public static final String USER_NOT_FOUND_OR_PASSWORD_INCORRECT = "Пользователь не найден или неверный пароль";
}
