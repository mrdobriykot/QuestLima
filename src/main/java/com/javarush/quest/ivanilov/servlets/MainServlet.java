package com.javarush.quest.ivanilov.servlets;

import com.javarush.quest.ivanilov.constants.Attributes;
import com.javarush.quest.ivanilov.constants.Targets;
import com.javarush.quest.ivanilov.entities.game.Game;
import com.javarush.quest.ivanilov.entities.game.Quest;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.AuthorizationService;
import com.javarush.quest.ivanilov.services.GameService;
import com.javarush.quest.ivanilov.services.QuestService;
import com.javarush.quest.ivanilov.utils.Navigator;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "MainServlet", value = Targets.MAIN)
public class MainServlet extends HttpServlet {

    AuthorizationService auth = AuthorizationService.AUTHORIZATION_SERVICE;
    QuestService questService = QuestService.QUEST_SERVICE;
    GameService gameService = GameService.GAME_SERVICE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        auth.authorizeAndProceed(req, resp, Targets.LOGIN);
        Collection<Quest> quests = questService.getAll();
        req.setAttribute(Attributes.QUESTS, quests);
        User user = (User) req.getSession().getAttribute(Attributes.USER);
        long currentGameId = user.getCurrentGameId();
        boolean isInGame = false;

        if (currentGameId != 0) {
            Game game = gameService.get(currentGameId);
            Quest quest = questService.get(game.getQuestId());
            req.setAttribute(Attributes.CURRENT_GAME_NAME, quest.getName());
            isInGame = true;
        }

        req.setAttribute(Attributes.IS_IN_GAME, isInGame);
        Navigator.dispatch(req, resp, Targets.MAIN_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
