package com.javarush.quest.shubchynskyi.questlima.controllers.user_controllers;

import com.javarush.quest.shubchynskyi.questlima.entity.user.User;
import com.javarush.quest.shubchynskyi.questlima.service.UserService;
import com.javarush.quest.shubchynskyi.questlima.util.Go;
import com.javarush.quest.shubchynskyi.questlima.util.Jsp;
import com.javarush.quest.shubchynskyi.questlima.util.Key;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "UsersServlet", value = Go.USERS)
public class UsersServlet extends HttpServlet {

    private final UserService userService = UserService.USER_SERVICE;

    public void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException {
        Collection<User> users = userService.getAll();
        request.setAttribute(Key.USERS, users);
        Jsp.forward(request, resp, Go.USERS);
    }

}