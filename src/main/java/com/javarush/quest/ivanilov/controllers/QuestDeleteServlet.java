package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.entities.game.Quest;
import com.javarush.quest.ivanilov.services.QuestService;
import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Jsp;
import com.javarush.quest.ivanilov.utils.constants.Messages;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "QuestDeleteServlet", value = Targets.QUEST_DELETE_ETC)
public class QuestDeleteServlet extends HttpServlet {
    QuestService questService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        questService = QuestService.QUEST_SERVICE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long questId = Long.parseLong(req.getParameter(Attributes.QUEST_ID));
        Quest questToBeDeleted = questService.get(questId);
        req.setAttribute(Attributes.QUEST, questToBeDeleted);
        Navigator.dispatch(req, resp, Jsp.QUEST_DELETE_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long questId = Long.parseLong(req.getParameter(Attributes.QUEST_ID));
            Quest questToBeDeleted = questService.get(questId);
            questService.delete(questId);
            req.setAttribute(Attributes.MESSAGE, new Messages().questDeleted(questToBeDeleted));
            Navigator.dispatch(req, resp, Jsp.SUCCESS_MESSAGE_JSP);
        } catch (Exception e) {
            Navigator.redirectError(req, resp, Messages.QUEST_NOT_DELETED, e);
        }
    }
}
