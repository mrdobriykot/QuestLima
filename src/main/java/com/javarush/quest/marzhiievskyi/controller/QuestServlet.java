package com.javarush.quest.marzhiievskyi.controller;

import com.javarush.quest.marzhiievskyi.entity.Quest;
import com.javarush.quest.marzhiievskyi.entity.Question;
import com.javarush.quest.marzhiievskyi.service.AnswerService;
import com.javarush.quest.marzhiievskyi.service.QuestService;
import com.javarush.quest.marzhiievskyi.service.QuestionService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

@WebServlet(name = "QuestServlet", value = "/quest")
public class QuestServlet extends HttpServlet {
    QuestService questService = QuestService.QUEST_SERVICE;
    QuestionService questionService = QuestionService.QUESTION_SERVICE;
    AnswerService answerService = AnswerService.ANSWER_SERVICE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questId = request.getParameter("id");
        Quest quest = questService.get(Long.parseLong(questId));



        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/quest.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.containsKey("agree")) {
            response.sendRedirect("/");
        } else if (parameterMap.containsKey("disagree")) {
            response.sendRedirect("profile");
        }
        //TODO not finished

    }
}
