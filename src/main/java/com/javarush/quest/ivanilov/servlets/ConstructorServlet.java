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

@WebServlet(name = "ConstructorServlet", value = Targets.CONSTRUCTOR)
public class ConstructorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(Attributes.QUEST_SAMPLE, BlackEarthQuest.QUEST);
        Navigator.dispatch(req, resp, Jsp.CONSTRUCTOR_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String questScenario = req.getParameter(Attributes.QUEST_SCENARIO);
        QuestParser parser = new QuestParser(
                QuestService.QUEST_SERVICE,
                EventService.EVENT_SERVICE,
                TaskService.TASK_SERVICE,
                HeroService.HERO_SERVICE);
        parser.parse(questScenario);
        Navigator.redirect(resp, Targets.MAIN);
    }
}
