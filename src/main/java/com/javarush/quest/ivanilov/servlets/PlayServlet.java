package com.javarush.quest.ivanilov.servlets;

import com.javarush.quest.ivanilov.constants.Attributes;
import com.javarush.quest.ivanilov.constants.Targets;
import com.javarush.quest.ivanilov.entities.game.Event;
import com.javarush.quest.ivanilov.entities.game.Game;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.AuthorizationService;
import com.javarush.quest.ivanilov.services.EventService;
import com.javarush.quest.ivanilov.services.GameService;
import com.javarush.quest.ivanilov.services.UserService;
import com.javarush.quest.ivanilov.utils.Navigator;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "PlayServlet", value = Targets.PLAY)
public class PlayServlet extends HttpServlet {
    AuthorizationService auth = AuthorizationService.AUTHORIZATION_SERVICE;
    GameService gameService = GameService.GAME_SERVICE;
    EventService eventService = EventService.EVENT_SERVICE;
    UserService userService = UserService.USER_SERVICE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        auth.authorizeAndProceed(req, resp, Targets.LOGIN_JSP);
        User user = (User) req.getSession().getAttribute(Attributes.USER);
        User player = userService.get(user.getId());
        Game game = gameService.get(player.getCurrentGameId());
        Event currEvent = eventService.get(game.getCurrEventId());
        req.getSession().setAttribute(Attributes.CURR_EVENT, currEvent);

        switch (currEvent.getEventType()) {
            case TASK -> Navigator.redirect(resp, Targets.DISPATCH_TASK);
            case WIN, LOSE -> Navigator.redirect(resp, Targets.SHOW_RESULT);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
