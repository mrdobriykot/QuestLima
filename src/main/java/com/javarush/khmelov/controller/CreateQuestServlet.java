package com.javarush.khmelov.controller;

import com.javarush.khmelov.config.Winter;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.QuestService;
import com.javarush.khmelov.util.Go;
import com.javarush.khmelov.util.Jsp;
import com.javarush.khmelov.util.Key;
import com.javarush.khmelov.util.Parser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(Go.CREATE)
public class CreateQuestServlet extends HttpServlet {

    private final QuestService questService = Winter.getBean(QuestService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jsp.forward(request, response, Go.CREATE);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(Key.NAME);
        String text = request.getParameter(Key.TEXT);
        Optional<User> optionalUser = Parser.getUser(request.getSession());
        optionalUser.ifPresent(user -> questService.create(name, text, user.getId()));
        Jsp.redirect(response, Go.HOME);
    }
}
