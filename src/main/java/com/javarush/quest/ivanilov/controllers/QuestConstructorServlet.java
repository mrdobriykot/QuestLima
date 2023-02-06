package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.services.*;
import com.javarush.quest.ivanilov.utils.constants.*;
import com.javarush.quest.ivanilov.utils.Navigator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebServlet(name = "QuestConstructorServlet", value = Targets.CONSTRUCTOR)
public class QuestConstructorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(Attributes.QUEST_SAMPLE, Strings.QUEST_SAMPLE);
        Navigator.dispatch(req, resp, Jsp.CONSTRUCTOR_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String questScenario = req.getParameter(Attributes.QUEST_SCENARIO);
            QuestParser parser = new QuestParser(
                    QuestService.QUEST_SERVICE,
                    EventService.EVENT_SERVICE,
                    TaskService.TASK_SERVICE,
                    HeroService.HERO_SERVICE);
            parser.parse(questScenario);
            Navigator.redirect(req, resp, Targets.MAIN);
        } catch (Exception e) {
            Navigator.redirectError(req, resp, Messages.QUEST_WAS_NOT_CREATED, e);
        }
    }
}
