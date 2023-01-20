package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.entity.Role;
import com.javarush.quest.khlopin.questlima.entity.User;
import jakarta.servlet.http.HttpSession;


public class CheckAdminService {
    private static boolean isAdmin = false;

    public static void checkAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getRole() == Role.ADMIN) {
            isAdmin = true;
            session.setAttribute("isAdmin", isAdmin);
        } else {
            isAdmin = false;
        }
    }

    public static void offAdmin(HttpSession session) {
        isAdmin = false;
        session.setAttribute("isAdmin",isAdmin);
    }
//    public static void setIsAdminParamInAttribute(HttpSession session) {
//        session.setAttribute("isAdmin", isAdmin);
//    }

}
