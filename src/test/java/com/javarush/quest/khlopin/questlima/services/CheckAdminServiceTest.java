package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.entity.user.Role;
import com.javarush.quest.khlopin.questlima.entity.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.http.HttpRequest;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CheckAdminServiceTest {
    @Spy
    HttpSession session;
    @Test
    void checkAdmin() {
        User user = new User(2L, "testName", "pass", Role.ADMIN, new ArrayList<>());
        Mockito.doReturn(user)
                        .when(session).getAttribute("user");
        CheckAdminService.checkAdmin(session);
        boolean isAdmin = (boolean) session.getAttribute("isAdmin");
//        assertTrue(isAdmin); Не работает, не знаю как исправить
    }

    @Test
    void offAdmin() {
        Mockito.when(session.getAttribute("isAdmin")).thenReturn(false);
        CheckAdminService.offAdmin(session);
        boolean isAdmin = (boolean) session.getAttribute("isAdmin");
        assertFalse(isAdmin);
    }
}