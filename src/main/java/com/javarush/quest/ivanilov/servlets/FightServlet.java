package com.javarush.quest.ivanilov.servlets;

import com.javarush.quest.ivanilov.constants.Attributes;
import com.javarush.quest.ivanilov.constants.Targets;
import com.javarush.quest.ivanilov.entities.game.Event;
import com.javarush.quest.ivanilov.entities.game.Fight;
import com.javarush.quest.ivanilov.entities.game.Game;
import com.javarush.quest.ivanilov.entities.game.Task;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.*;
import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.RequestUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "FightServlet", value = Targets.FIGHT)
public class FightServlet extends HttpServlet {
    AuthorizationService auth = AuthorizationService.AUTHORIZATION_SERVICE;
    GameWorker gameWorker = new GameWorkerImpl();
    TaskService taskService = TaskService.TASK_SERVICE;
    GameService gameService = GameService.GAME_SERVICE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        auth.authorizeAndProceed(req, resp, Targets.LOGIN_JSP);
        Event event = (Event) req.getSession().getAttribute(Attributes.CURR_EVENT);
        Fight fight = gameWorker.getFightToSend(req.getSession(), event);
        req.setAttribute(Attributes.MESSAGE, event.getTask().getDescription());
        req.getSession().setAttribute(Attributes.FIGHT, fight);
        Navigator.dispatch(req, resp, Targets.FIGHT_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        auth.authorizeAndProceed(req, resp, Targets.LOGIN_JSP);
        Map<String, String> attributes = RequestUtils.getAttributes(req);
        String hitOptions = attributes.get(Attributes.HIT_OPTION);
        String blockOptions = attributes.get(Attributes.BLOCK_OPTION);
        HttpSession session = req.getSession();

        if (StringUtils.isEmpty(hitOptions) || StringUtils.isEmpty(blockOptions)) {
            Navigator.redirect(resp, Targets.FIGHT);
        }

        Fight currFight = (Fight) session.getAttribute(Attributes.FIGHT);
        Fight fight = gameWorker.fight(currFight, hitOptions, blockOptions);

        switch (fight.getStatus()) {
            case TASK -> {
                session.setAttribute(Attributes.FIGHT, fight);
                Navigator.redirect(resp, Targets.FIGHT);
            }
            case WIN -> {
                User user = (User) session.getAttribute(Attributes.USER);
                Task task = taskService.get(fight.getTaskId());
                Game game = gameService.get(user.getCurrentGameId());
                game.setCurrEventId(task.getNextEventIfFightWasWon().getId());
                game.setHero(fight.getHero());
                gameService.update(game);
                Navigator.redirect(resp, Targets.PLAY); //TODO Почти идентичный код для WIN и LOSE
            }
            case LOSE -> {
                User user = (User) session.getAttribute(Attributes.USER);
                Task task = taskService.get(fight.getTaskId());
                Game game = gameService.get(user.getCurrentGameId());
                game.setCurrEventId(task.getNextEventIfFightWasLost().getId());
                game.setHero(fight.getHero());
                gameService.update(game);
                Navigator.redirect(resp, Targets.PLAY);
            }
            default -> throw new UnsupportedOperationException();
        }
    }
}
