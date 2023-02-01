package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.entities.game.Quest;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.AuthorizationService;
import com.javarush.quest.ivanilov.services.QuestService;
import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.Transfer;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Jsp;
import com.javarush.quest.ivanilov.utils.constants.Messages;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import com.javarush.quest.ivanilov.utils.transfers.QuestDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "QuestsServlet", value = Targets.QUESTS)
public class QuestsServlet extends HttpServlet {
    AuthorizationService auth = AuthorizationService.AUTHORIZATION_SERVICE;
    QuestService questService = QuestService.QUEST_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Attributes.USER);

        if (auth.isAdmin(user)) {
            Collection<Quest> quests = questService.getAll();
            Collection<QuestDto> questsToSend = new Transfer().extractQuestsToSend(quests);
            req.setAttribute(Attributes.QUESTS, questsToSend);
            Navigator.dispatch(req, resp, Jsp.QUESTS_JSP);
        } else {
            req.setAttribute(Attributes.MESSAGE, new Messages().forbidden(user.getLogin()));
            Navigator.dispatch(req, resp, Jsp.ERROR_MESSAGE_JSP);
        }
    }
}
