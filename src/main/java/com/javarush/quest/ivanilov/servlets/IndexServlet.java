package com.javarush.quest.ivanilov.servlets;

import com.javarush.quest.ivanilov.config.BlackEarthQuest;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Jsp;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import com.javarush.quest.ivanilov.controllers.*;
import com.javarush.quest.ivanilov.utils.Navigator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "IndexServlet", value = Targets.INDEX, loadOnStartup = 0)
public class IndexServlet extends HttpServlet {

    @Override
    public void init() {
        QuestParser questParser =
                new QuestParser(QuestService.QUEST_SERVICE,
                        EventService.EVENT_SERVICE,
                        TaskService.TASK_SERVICE,
                        HeroService.HERO_SERVICE);
        questParser.parse(BlackEarthQuest.QUEST);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isAuthorized = Boolean.getBoolean(req.getParameter(Attributes.IS_AUTHORIZED));
        if (isAuthorized) {
            Navigator.redirect(resp, Targets.MAIN);
        } else {
            Navigator.dispatch(req, resp, Jsp.INDEX_JSP);
        }
    }
}
