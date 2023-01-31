package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.entities.game.Event;
import com.javarush.quest.ivanilov.entities.game.GameStatus;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Jsp;
import com.javarush.quest.ivanilov.utils.constants.Strings;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ShowResultServlet", value = Targets.SHOW_RESULT)
public class ShowResultServlet extends HttpServlet {

    GameWorkerImpl gameWorker = new GameWorkerImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Event event = (Event) req.getSession().getAttribute(Attributes.CURR_EVENT);
        req.setAttribute(Attributes.MESSAGE, event.getMessage());
        User user = (User) req.getSession().getAttribute(Attributes.USER);
        GameStatus status = gameWorker.endGame(event, user).getStatus();
        String message;
        if (status.equals(GameStatus.WON)) {
            message = Strings.WON;
        } else {
            message = Strings.LOST;
        }
        req.setAttribute(Attributes.QUEST_RESULT, message);
        Navigator.dispatch(req, resp, Jsp.SHOW_RESULT_JSP);
    }
}
