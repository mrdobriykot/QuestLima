package com.javarush.quest.khlopin.questlima.controllers.questControllers;

import com.javarush.quest.khlopin.questlima.services.FirstQuestStarter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "FirstQuestServlet", value = "/quest")
public class FirstQuestServlet extends HttpServlet {

    private final FirstQuestStarter questStarter = new FirstQuestStarter();
    private int stage = 0;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        questStarter.startQuest(request);

        request.getRequestDispatcher("WEB-INF/quests/firstQuest/quest.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String answer = request.getParameter("answer");

        if (answer == null) {
            request.getRequestDispatcher("WEB-INF/quests/errorCase.jsp").forward(request, response);
        } else {
            if (answer.equals("false")) {
                questStarter.nextStageOfQuest(request, answer, stage);
                request.getRequestDispatcher("WEB-INF/quests/firstQuest/quest.jsp").forward(request, response);
            } else {
                questStarter.nextStageOfQuest(request, answer, stage);
                stage++;
                request.getRequestDispatcher("WEB-INF/quests/firstQuest/quest.jsp").forward(request, response);
            }
        }
    }
}
