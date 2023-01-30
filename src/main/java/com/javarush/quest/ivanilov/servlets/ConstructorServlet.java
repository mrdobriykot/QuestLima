package com.javarush.quest.ivanilov.servlets;

import com.javarush.quest.ivanilov.config.BlackEarthQuest;
import com.javarush.quest.ivanilov.constants.Attributes;
import com.javarush.quest.ivanilov.constants.Targets;
import com.javarush.quest.ivanilov.services.*;
import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.RequestUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "ConstructorServlet", value = Targets.CONSTRUCTOR)
public class ConstructorServlet extends HttpServlet {

    public static final String QUEST_SCENARIO = "questScenario";
    AuthorizationService auth = AuthorizationService.AUTHORIZATION_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        auth.authorizeAndProceed(req, resp, Targets.LOGIN);
        req.setAttribute(Attributes.QUEST_SAMPLE, BlackEarthQuest.QUEST);
        Navigator.dispatch(req, resp, Targets.CONSTRUCTOR_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        auth.authorizeAndProceed(req, resp, Targets.LOGIN);
        Map<String, String> attributes = RequestUtils.getAttributes(req);
        String questScenario = attributes.get(QUEST_SCENARIO);
        QuestParser parser = new QuestParser(
                QuestService.QUEST_SERVICE,
                EventService.EVENT_SERVICE,
                TaskService.TASK_SERVICE,
                HeroService.HERO_SERVICE);
        parser.initQuest(questScenario);
        Navigator.redirect(resp, Targets.MAIN);
    }
}
