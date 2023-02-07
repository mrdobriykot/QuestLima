package com.javarush.quest.uzienko.questlima.controller;

import com.javarush.quest.uzienko.questlima.config.UrlConfig;
import com.javarush.quest.uzienko.questlima.config.Winter;
import com.javarush.quest.uzienko.questlima.entity.Quest;
import com.javarush.quest.uzienko.questlima.service.QuestService;
import com.javarush.quest.uzienko.questlima.utils.Jsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "QuestMainPage", value = UrlConfig.ENDPOINT_QUEST_MAIN_PAGE)
public class QuestMainPageServlet extends HttpServlet {

    private final QuestService questService = Winter.getBean(QuestService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Quest quest = getQuest(req);
        if (quest != null) {
            req.setAttribute("quest", quest);
            Jsp.include(req, resp, UrlConfig.VIEW_FOR_QUEST_MAIN_PAGE_SERVLET);
        } else {
            Jsp.forward(req, resp, UrlConfig.ENDPOINT_ERROR_404);
        }
    }

    private Quest getQuest(HttpServletRequest req) {
        return Optional.ofNullable(req.getParameter("id"))
                .map(Long::valueOf)
                .flatMap(questService::get)
                .orElse(null);
    }
}
