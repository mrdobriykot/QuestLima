package com.javarush.quest.torkel.controller;

import com.javarush.quest.torkel.config.Winter;
import com.javarush.quest.torkel.entity.User;
import com.javarush.quest.torkel.service.UserService;
import com.javarush.quest.torkel.util.Go;
import com.javarush.quest.torkel.util.Jsp;
import com.javarush.quest.torkel.util.Key;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "UsersServlet", value = Go.USERS)
public class UsersServlet extends HttpServlet {

    private final UserService userService = Winter.getBean(UserService.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Collection<User> users = userService.getAll();
        request.setAttribute(Key.USERS, users);
        Jsp.forward(request, response, Key.USERS);
    }


    public void destroy() {
    }
}
