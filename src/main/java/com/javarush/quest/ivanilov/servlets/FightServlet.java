package com.javarush.quest.ivanilov.servlets;

import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Jsp;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import com.javarush.quest.ivanilov.entities.game.Event;
import com.javarush.quest.ivanilov.entities.game.Fight;
import com.javarush.quest.ivanilov.entities.game.Game;
import com.javarush.quest.ivanilov.entities.game.Task;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.controllers.GameService;
import com.javarush.quest.ivanilov.controllers.GameWorker;
import com.javarush.quest.ivanilov.controllers.GameWorkerImpl;
import com.javarush.quest.ivanilov.controllers.TaskService;
import com.javarush.quest.ivanilov.utils.Navigator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@WebServlet(name = "FightServlet", value = Targets.FIGHT)
public class FightServlet extends HttpServlet {
    GameWorker gameWorker = new GameWorkerImpl();
    TaskService taskService = TaskService.TASK_SERVICE;
    GameService gameService = GameService.GAME_SERVICE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isAuthorized = Boolean.getBoolean(req.getParameter(Attributes.IS_AUTHORIZED));
        if (!isAuthorized) {
            Navigator.redirect(resp, Targets.LOGIN);
        }

        Event event = (Event) req.getSession().getAttribute(Attributes.CURR_EVENT);
        Fight fight = gameWorker.getFightToSend(req.getSession(), event);
        req.setAttribute(Attributes.MESSAGE, event.getTask().getDescription());
        req.getSession().setAttribute(Attributes.FIGHT, fight);
        Navigator.dispatch(req, resp, Jsp.FIGHT_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String hitOptions = req.getParameter(Attributes.HIT_OPTION);
        String blockOptions = req.getParameter(Attributes.BLOCK_OPTION);
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
                finishFight(fight, user, true);
                Navigator.redirect(resp, Targets.PLAY);
            }
            case LOSE -> {
                User user = (User) session.getAttribute(Attributes.USER);
                finishFight(fight, user, false);
                Navigator.redirect(resp, Targets.PLAY);
            }
            default -> throw new UnsupportedOperationException();
        }
    }

    private void finishFight(Fight fight, User user, boolean isWon) {
        Task task = taskService.get(fight.getTaskId());
        Game game = gameService.get(user.getCurrentGameId());
        if (isWon) {
            game.setCurrEventId(task.getNextEventIfFightWasWon().getId());
        } else {
            game.setCurrEventId(task.getNextEventIfFightWasLost().getId());
        }
        game.setHero(fight.getHero());
        gameService.update(game);
    }
}
