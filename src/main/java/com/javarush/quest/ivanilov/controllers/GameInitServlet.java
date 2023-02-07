package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.services.*;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Messages;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.utils.Navigator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "GameInitServlet", value = Targets.NEW_GAME)
public class GameInitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Navigator.redirect(req, resp, Targets.PLAY);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            User player = (User) req.getSession().getAttribute(Attributes.USER);
            long questId = Long.parseLong(req.getParameter(Attributes.QUEST_ID));
            GameWorker worker = new GameWorkerImpl(GameService.GAME_SERVICE, UserService.USER_SERVICE, QuestService.QUEST_SERVICE);
            worker.initializeGame(player.getId(), questId);
            Navigator.redirect(req, resp, Targets.PLAY);
        } catch (Exception e) {
            Navigator.redirectError(req, resp, Messages.GAME_NOT_INITIALIZED, e);
        }
    }
}
