package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.entities.game.Quest;
import com.javarush.quest.ivanilov.services.QuestService;
import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.Transfer;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Jsp;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import com.javarush.quest.ivanilov.utils.transfers.QuestDto;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "QuestsServlet", value = Targets.QUESTS)
public class QuestsServlet extends HttpServlet {
    QuestService questService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        questService = QuestService.QUEST_SERVICE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Quest> quests = questService.getAll();
        Collection<QuestDto> questsToSend = new Transfer().extractQuestsToSend(quests);
        req.setAttribute(Attributes.QUESTS, questsToSend);
        Navigator.dispatch(req, resp, Jsp.QUESTS_JSP);
    }
}
