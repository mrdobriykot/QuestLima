package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.services.EventService;
import com.javarush.quest.ivanilov.services.GameService;
import com.javarush.quest.ivanilov.services.UserService;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import com.javarush.quest.ivanilov.entities.game.Event;
import com.javarush.quest.ivanilov.entities.game.Game;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.utils.Navigator;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "PlayServlet", value = Targets.PLAY)
public class PlayServlet extends HttpServlet {
    GameService gameService;
    EventService eventService;
    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gameService = GameService.GAME_SERVICE;
        eventService = EventService.EVENT_SERVICE;
        userService = UserService.USER_SERVICE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Attributes.USER);
        User player = userService.get(user.getId());
        Game game = gameService.get(player.getCurrentGameId());
        Event currEvent = eventService.get(game.getCurrEventId());
        req.getSession().setAttribute(Attributes.CURR_EVENT, currEvent);

        switch (currEvent.getEventType()) {
            case TASK -> Navigator.redirect(req, resp, Targets.DISPATCH_TASK);
            case WIN, LOSE -> Navigator.redirect(req, resp, Targets.SHOW_RESULT);
        }
    }
}
