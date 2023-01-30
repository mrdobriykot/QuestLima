package com.javarush.quest.ivanilov.servlets;

import com.javarush.quest.ivanilov.config.BlackEarthQuest;
import com.javarush.quest.ivanilov.constants.Targets;
import com.javarush.quest.ivanilov.services.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "IndexServlet", value = Targets.INDEX, loadOnStartup = 0)
public class IndexServlet extends HttpServlet {
    private final AuthorizationService auth = AuthorizationService.AUTHORIZATION_SERVICE;

    @Override
    public void init() throws ServletException {
        QuestParser questParser =
                new QuestParser(QuestService.QUEST_SERVICE,
                        EventService.EVENT_SERVICE,
                        TaskService.TASK_SERVICE,
                        HeroService.HERO_SERVICE);
        questParser.initQuest(BlackEarthQuest.QUEST);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        auth.authorizeAndProceed(req, resp, Targets.MAIN, Targets.INDEX_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
