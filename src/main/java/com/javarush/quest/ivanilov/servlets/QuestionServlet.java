package com.javarush.quest.ivanilov.servlets;

import com.javarush.quest.ivanilov.constants.Attributes;
import com.javarush.quest.ivanilov.constants.Targets;
import com.javarush.quest.ivanilov.entities.game.Event;
import com.javarush.quest.ivanilov.entities.game.Game;
import com.javarush.quest.ivanilov.entities.game.Option;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.*;
import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.RequestUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "QuestionServlet", value = Targets.QUESTION)
public class QuestionServlet extends HttpServlet {

    GameWorker gameWorker = new GameWorkerImpl();
    EventService eventService = EventService.EVENT_SERVICE;
    AuthorizationService auth = AuthorizationService.AUTHORIZATION_SERVICE;
    UserService userService = UserService.USER_SERVICE;
    GameService gameService = GameService.GAME_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        auth.authorizeAndProceed(req, resp, Targets.LOGIN_JSP);
        Event event = (Event) req.getSession().getAttribute(Attributes.CURR_EVENT);
        Option[] optionsToSend = gameWorker.getOptionsToSend(eventService.get(event.getId()));
        req.setAttribute(Attributes.MESSAGE, event.getTask().getDescription());
        req.setAttribute(Attributes.OPTIONS, optionsToSend);
        Navigator.dispatch(req, resp, Targets.QUESTION_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        auth.authorizeAndProceed(req, resp, Targets.LOGIN_JSP);
        HttpSession session = req.getSession();
        Event event = (Event) session.getAttribute(Attributes.CURR_EVENT);
        Map<String, String> attributes = RequestUtils.getAttributes(req);
        int optionId = Integer.parseInt(attributes.getOrDefault(Attributes.OPTION_ID, Attributes.EMPTY));
        Event resultEvent = gameWorker.validateAnswer(event, optionId);
        session.setAttribute(Attributes.CURR_EVENT, resultEvent);
        User user = (User) session.getAttribute(Attributes.USER);
        Game game = gameService.get(user.getCurrentGameId());
        game.setCurrEventId(resultEvent.getId());
        gameService.update(game);
        Navigator.redirect(resp, Targets.PLAY);
    }
}
