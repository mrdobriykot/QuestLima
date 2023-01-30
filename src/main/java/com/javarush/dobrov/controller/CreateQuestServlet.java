package com.javarush.dobrov.controller;

import com.javarush.dobrov.service.QuestService;
import com.javarush.dobrov.service.QuestionService;
import com.javarush.dobrov.util.SingleEntity;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CreateQuestServlet", value = "/CreateQuestServlet")
public class CreateQuestServlet extends HttpServlet {

    private final QuestService questService = SingleEntity.getInstance(QuestService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/create_quest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String quest_text =  request.getParameter("quest_text");
        String quest_header =  request.getParameter("quest_header");

        questService.createQuest(quest_header,quest_text);

    }
}
