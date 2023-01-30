package com.javarush.quest.ivanilov.servlets;

import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.controllers.GameWorker;
import com.javarush.quest.ivanilov.controllers.GameWorkerImpl;
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
        Navigator.redirect(resp, Targets.PLAY);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User player = (User) req.getSession().getAttribute(Attributes.USER);
        long questId = Long.parseLong(req.getParameter(Attributes.QUEST_ID));
        GameWorker worker = new GameWorkerImpl();
        worker.initializeGame(player.getId(), questId);
        Navigator.redirect(resp, Targets.PLAY);
    }
}
