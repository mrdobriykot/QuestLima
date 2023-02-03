package com.bogdanov.util;

import com.bogdanov.entity.Role;
import com.bogdanov.entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class Jsp {
    public static void forward(HttpServletRequest request, HttpServletResponse response, String target) throws ServletException, IOException {
        target = target.replace("/","");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/%s.jsp".formatted(target));
        requestDispatcher.forward(request, response);
    }
    public static User getUserCunstructor(HttpServletRequest request) {
        User user = User.builder()
                .id(Long.valueOf(request.getParameter(Key.ID)))
                .login(request.getParameter(Key.LOGIN))
                .password(request.getParameter(Key.PASSWORD))
                .role(Role.valueOf(request.getParameter(Key.ROLE)))
                .build();
        return user;
    }
    public static void response(HttpServletResponse response, String target) throws IOException {
        target = target.replace("/","");
        response.sendRedirect(target);
    }
}
