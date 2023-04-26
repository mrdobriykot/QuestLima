package com.javarush.khmelov.controller;

import com.javarush.khmelov.config.Spring;
import com.javarush.khmelov.dto.UserTo;
import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.util.Go;
import com.javarush.khmelov.util.Jsp;
import com.javarush.khmelov.util.Key;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet(Go.LOGIN)
public class LoginServlet extends HttpServlet {

    private final UserService userService = Spring.getBean(UserService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jsp.forward(request, response, Go.LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(Key.LOGIN);
        String password = request.getParameter(Key.PASSWORD);
        Optional<UserTo> user = userService.get(login, password);
        if (user.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute(Key.USER, user.get());
            Jsp.redirect(response, Go.PROFILE);
        } else {
            Jsp.redirect(response, Go.LOGIN); //todo add error message
        }
    }
}
