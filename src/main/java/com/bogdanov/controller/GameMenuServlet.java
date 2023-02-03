package com.bogdanov.controller;

import com.bogdanov.service.QuestService;
import com.bogdanov.util.Go;
import com.bogdanov.util.Jsp;
import com.bogdanov.util.Key;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "GameMenuServlet", value = Go.GAME_MENU)
public class GameMenuServlet extends HttpServlet {





    public final QuestService  service= QuestService.QUEST_SERVICE;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(Key.QUESTS,service.getAll());
        Jsp.forward(request, response, Go.GAME_MENU);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doPost(req, resp);
    }

    public void destroy() {

    }
}