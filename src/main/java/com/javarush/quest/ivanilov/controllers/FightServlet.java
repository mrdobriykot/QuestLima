package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.entities.game.*;
import com.javarush.quest.ivanilov.services.*;
import com.javarush.quest.ivanilov.utils.constants.*;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.utils.Navigator;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Collections;

@Log4j2
@WebServlet(name = "FightServlet", value = Targets.FIGHT)
public class FightServlet extends HttpServlet {
    GameWorker gameWorker;
    TaskService taskService;
    GameService gameService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gameWorker = new GameWorkerImpl(
                GameService.GAME_SERVICE,
                UserService.USER_SERVICE,
                QuestService.QUEST_SERVICE
        );
        taskService = TaskService.TASK_SERVICE;
        gameService = GameService.GAME_SERVICE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Event event = (Event) req.getSession().getAttribute(Attributes.CURR_EVENT);
        Fight fight = gameWorker.getFightToSend(req.getSession(), event);
        Collections.reverse(fight.getFightLog());
        req.setAttribute(Attributes.MESSAGE, event.getTask().getDescription());
        req.getSession().setAttribute(Attributes.FIGHT, fight);
        Navigator.dispatch(req, resp, Jsp.FIGHT_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String hitOptions = req.getParameter(Attributes.HIT_OPTION);
            String blockOptions = req.getParameter(Attributes.BLOCK_OPTION);
            HttpSession session = req.getSession();

            if (StringUtils.isEmpty(hitOptions) || StringUtils.isEmpty(blockOptions)) {
                Navigator.redirect(req, resp, Targets.FIGHT);
            }

            Fight currFight = (Fight) session.getAttribute(Attributes.FIGHT);
            Collections.reverse(currFight.getFightLog());
            Fight fight = gameWorker.fight(currFight, hitOptions, blockOptions);

            switch (fight.getStatus()) {
                case TASK -> {
                    session.setAttribute(Attributes.FIGHT, fight);
                    Navigator.redirect(req, resp, Targets.FIGHT);
                    log.info(Logs.FIGHT_LOG, fight.getHero(), fight.getVillain(), fight.getStatus());
                }
                case WIN -> {
                    User user = (User) session.getAttribute(Attributes.USER);
                    finishFight(fight, user, true);
                    session.setAttribute(Attributes.FIGHT, null);
                    Navigator.redirect(req, resp, Targets.PLAY);
                    log.info(Logs.FIGHT_RESULTS, fight.getHero(), fight.getVillain());
                }
                case LOSE -> {
                    User user = (User) session.getAttribute(Attributes.USER);
                    finishFight(fight, user, false);
                    session.setAttribute(Attributes.FIGHT, null);
                    Navigator.redirect(req, resp, Targets.PLAY);
                    log.info(Logs.FIGHT_RESULTS, fight.getVillain(), fight.getHero());
                }
                default -> throw new UnsupportedOperationException();
            }
        } catch (Exception e) {
            Navigator.redirectError(req, resp, Messages.FIGHT_ERROR, e);
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
