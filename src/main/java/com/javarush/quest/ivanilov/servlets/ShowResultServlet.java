package com.javarush.quest.ivanilov.servlets;

import com.javarush.quest.ivanilov.constants.Attributes;
import com.javarush.quest.ivanilov.constants.Strings;
import com.javarush.quest.ivanilov.constants.Targets;
import com.javarush.quest.ivanilov.entities.game.Event;
import com.javarush.quest.ivanilov.entities.game.GameStatus;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.AuthorizationService;
import com.javarush.quest.ivanilov.services.GameWorkerImpl;
import com.javarush.quest.ivanilov.utils.Navigator;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ShowResultServlet", value = Targets.SHOW_RESULT)
public class ShowResultServlet extends HttpServlet {

    AuthorizationService auth = AuthorizationService.AUTHORIZATION_SERVICE;
    GameWorkerImpl gameWorker = new GameWorkerImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        auth.authorizeAndProceed(req, resp, Targets.LOGIN_JSP);
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
        Navigator.dispatch(req, resp, Targets.SHOW_RESULT_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
