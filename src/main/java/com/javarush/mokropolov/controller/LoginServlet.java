package com.javarush.mokropolov.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Optional;

import com.javarush.mokropolov.config.Winter;
import com.javarush.mokropolov.entity.User;
import com.javarush.mokropolov.service.UserService;
import com.javarush.mokropolov.util.Go;
import com.javarush.mokropolov.util.Jsp;
import com.javarush.mokropolov.util.Key;

@WebServlet(name = "LoginServlet", value = Go.LOGIN)
public class LoginServlet extends HttpServlet {

    private final UserService userService = Winter.getBean(UserService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jsp.forward(request, response, Go.LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(Key.LOGIN);
        String password = request.getParameter(Key.PASSWORD);
        Optional<User> user = userService.get(login, password);
        if (user.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute(Key.USER,user.get());
            Jsp.redirect(response,Go.PROFILE);
        } else {
            Jsp.redirect(response,Go.LOGIN); //todo add error message
        }
    } 
}
