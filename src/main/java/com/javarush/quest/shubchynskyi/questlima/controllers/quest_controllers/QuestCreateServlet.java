package com.javarush.quest.shubchynskyi.questlima.controllers.quest_controllers;

import com.javarush.quest.shubchynskyi.questlima.entity.game.Quest;
import com.javarush.quest.shubchynskyi.questlima.entity.user.User;
import com.javarush.quest.shubchynskyi.questlima.service.QuestService;
import com.javarush.quest.shubchynskyi.questlima.util.Go;
import com.javarush.quest.shubchynskyi.questlima.util.Jsp;
import com.javarush.quest.shubchynskyi.questlima.util.Key;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CreateQuestServlet", value = Go.CREATE_QUEST)
public class QuestCreateServlet extends HttpServlet {

    private final QuestService questService = QuestService.QUEST_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Jsp.isParameterPresent(request, Key.USER)) {
            Jsp.forward(request, response, Key.CREATE_QUEST);
        } else {
            response.sendRedirect(Key.LOGIN);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String questTitle = request.getParameter(Key.NAME);
        String questText = request.getParameter(Key.TEXT);
        String questDescription = request.getParameter(Key.DESCRIPTION);
        //TODO если пользователя нет, то редирект на логин
        Long userId = ((User) request.getSession().getAttribute(Key.USER)).getId();
        Quest quest = questService.create(questTitle, questText, questDescription, userId);

        Jsp.redirect(response, Key.S_ID_S_URI_PATTERN.formatted(Go.QUEST_EDIT, quest.getId()));
    }
}
