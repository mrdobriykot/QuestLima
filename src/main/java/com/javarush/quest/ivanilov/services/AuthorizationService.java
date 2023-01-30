package com.javarush.quest.ivanilov.services;

import com.javarush.quest.ivanilov.constants.Attributes;
import com.javarush.quest.ivanilov.constants.Logs;
import com.javarush.quest.ivanilov.entities.users.Roles;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.utils.Navigator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public enum AuthorizationService {

    AUTHORIZATION_SERVICE;

    public boolean isAuthorized(HttpServletRequest req) {
        boolean isAuthorized = false;

        if (req.getSession().getAttribute(Attributes.IS_AUTHORIZED) != null) {
            isAuthorized = (boolean) req.getSession().getAttribute(Attributes.IS_AUTHORIZED);
        }

        User user = (User) req.getSession().getAttribute(Attributes.USER);

        if (user != null) {
            log.info(String.format(Logs.AUTHORIZED_SUCCESSFULLY, user));
        }

        return isAuthorized;
    }

    public boolean authorize(User user, String password) {
        if (user == null || password == null) {
            return false;
        }

        String actualPassword = user.getPassword();
        return actualPassword.equals(password);
    }

    public void authorizeAndProceed(HttpServletRequest req, HttpServletResponse resp, String targetIfAuthorized, String targetIfNotAuthorized) throws ServletException, IOException {
        boolean isAuthorized = this.isAuthorized(req);

        if (isAuthorized) {
            Navigator.redirect(resp, targetIfAuthorized);
        } else {
            Navigator.redirect(resp, targetIfNotAuthorized);
        }
    }

    public void authorizeAndProceed(HttpServletRequest req, HttpServletResponse resp, String targetIfNotAuthorized) throws ServletException, IOException {
        boolean isAuthorized = this.isAuthorized(req);

        if (!isAuthorized) {
            Navigator.redirect(resp, targetIfNotAuthorized);
        }
    }

    public boolean isAdmin(User user) {
        return user.getRole() == Roles.ADMIN;
    }

}
