package com.javarush.quest.khlopin.questlima.controllers.questControllers;

import com.javarush.quest.khlopin.questlima.services.QuestStarter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "FirstQuestServlet", value = "/quest")
public class QuestServlet extends HttpServlet {

    private QuestStarter questStarter;
    private int stage = 0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        questStarter = new QuestStarter();
        questStarter.startQuest(request);

        request.getRequestDispatcher("WEB-INF/quests/firstQuest/quest.jsp").forward(request, response);
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
                request.setAttribute("result", null);
                if (stage != questStarter.getQuest().getCountOfStages()) {
                    stage++;
                }
                if (stage == questStarter.getQuest().getCountOfStages() && answer.equals("true")) { //TODO Перенести это в questStarter и покрыть лоагми
                    stage = 0;
                    questStarter.findTrueAnswer();
                    request.setAttribute("result", questStarter.getFinishTrueAnswer());
                    request.getRequestDispatcher("WEB-INF/quests/firstQuest/quest.jsp").forward(request, response);
                } else {
                    questStarter.nextStageOfQuest(request, answer, stage);
                    System.out.println(stage);
                    request.getRequestDispatcher("WEB-INF/quests/firstQuest/quest.jsp").forward(request, response);
                }
            }
        }
    }
}
