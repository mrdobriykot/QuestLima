package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.entity.user.Role;
import com.javarush.quest.khlopin.questlima.entity.user.User;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CheckAdminService {
    private static boolean isAdmin = false;
    private static final Logger log = LogManager.getLogger(CheckAdminService.class);

    public static void checkAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getRole() == Role.ADMIN) {
            isAdmin = true;
            session.setAttribute("isAdmin", isAdmin);
            log.warn(user + " авторизовался как администратор");
        } else {
            isAdmin = false;
        }
    }

    public static void offAdmin(HttpSession session) {
        isAdmin = false;
        session.setAttribute("isAdmin",isAdmin);
    }


}
