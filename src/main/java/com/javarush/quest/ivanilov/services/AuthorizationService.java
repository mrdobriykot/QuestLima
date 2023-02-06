package com.javarush.quest.ivanilov.services;

import com.javarush.quest.ivanilov.entities.users.Role;
import com.javarush.quest.ivanilov.entities.users.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum AuthorizationService {

    AUTHORIZATION_SERVICE;

    public boolean authorize(User user, String password) {
        if (user == null || password == null) {
            return false;
        }
        String actualPassword = user.getPassword();
        return actualPassword.equals(password);
    }

    public boolean isAdmin(User user) {
        return user.getRole() == Role.ADMIN;
    }
}
