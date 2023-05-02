package com.javarush.mokropolov.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Optional;

import com.javarush.mokropolov.config.Winter;
import com.javarush.mokropolov.entity.User;
import com.javarush.mokropolov.service.QuestService;
import com.javarush.mokropolov.util.Go;
import com.javarush.mokropolov.util.Jsp;
import com.javarush.mokropolov.util.Key;
import com.javarush.mokropolov.util.Parser;

@WebServlet(name = "CreateQuestServlet", value = Go.CREATE_QUEST)
public class CreateQuestServlet extends HttpServlet {
  
    private final QuestService questService = Winter.getBean(QuestService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jsp.forward(request, response, Key.CREATE_QUEST);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(Key.NAME);
        String text = request.getParameter(Key.TEXT);
        Optional<User> optionalUser = Parser.getUser(request.getSession());
        optionalUser.ifPresent(user -> questService.create(name, text, user.getId()));
        Jsp.redirect(response, Go.HOME);
    }
}
