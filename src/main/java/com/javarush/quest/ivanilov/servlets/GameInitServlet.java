package com.javarush.quest.ivanilov.servlets;

import com.javarush.quest.ivanilov.constants.Attributes;
import com.javarush.quest.ivanilov.constants.Targets;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.AuthorizationService;
import com.javarush.quest.ivanilov.services.GameWorker;
import com.javarush.quest.ivanilov.services.GameWorkerImpl;
import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.RequestUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "GameInitServlet", value = Targets.NEW_GAME)
public class GameInitServlet extends HttpServlet {

    AuthorizationService auth = AuthorizationService.AUTHORIZATION_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        auth.authorizeAndProceed(req, resp, Targets.PLAY, Targets.LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        auth.authorizeAndProceed(req, resp, Targets.LOGIN);
        Map<String, String> attributes = RequestUtils.getAttributes(req);
        User player = (User) req.getSession().getAttribute(Attributes.USER);
        long questId = Long.parseLong(attributes.get(Attributes.QUEST_ID));
        GameWorker worker = new GameWorkerImpl();
        worker.initializeGame(player.getId(), questId);
        Navigator.redirect(resp, Targets.PLAY);
    }
}
