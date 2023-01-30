package com.javarush.quest.ivanilov.servlets;

import com.javarush.quest.ivanilov.controllers.GameService;
import com.javarush.quest.ivanilov.controllers.QuestService;
import com.javarush.quest.ivanilov.entities.game.Game;
import com.javarush.quest.ivanilov.entities.game.Quest;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Jsp;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "MainServlet", value = Targets.MAIN)
public class MainServlet extends HttpServlet {
    QuestService questService = QuestService.QUEST_SERVICE;
    GameService gameService = GameService.GAME_SERVICE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        Navigator.dispatch(req, resp, Jsp.MAIN_JSP);
    }
}
