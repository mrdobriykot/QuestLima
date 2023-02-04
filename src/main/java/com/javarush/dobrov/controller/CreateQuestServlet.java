package com.javarush.dobrov.controller;

import com.javarush.dobrov.entity.Answer;
import com.javarush.dobrov.entity.Question;
import com.javarush.dobrov.service.QuestService;
import com.javarush.dobrov.util.SingleEntity;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "CreateQuestServlet", value = "/CreateQuestServlet")
public class CreateQuestServlet extends HttpServlet {

    private final QuestService questService = SingleEntity.getInstance(QuestService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/create_quest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String quest_text = request.getParameter("quest_text");
        String quest_header = request.getParameter("quest_header");

        request.setAttribute("quest_header", quest_header);

        questService.createQuest(quest_header, quest_text);

      //  int questionIdCount=0;
        int answerMultiplier=0;
        Map<Long, Question> questions = questService.getQuestions();
        Map<Long, Answer> answers = questService.getAnswers();
        List<Answer> answerList = new ArrayList<>();

        Map<Integer, Integer> answersConnecting = questService.answersConnecting;

    //    for (int i = 1; i <= questions.size(); i++) {

            request.setAttribute("question", questions.get((long) 1).getQuestionText());

            Integer numberOfAnswers = answersConnecting.get(1);
            System.out.println("numberAnswers "+numberOfAnswers);
       //     answerMultiplier=questionIdCount;
            for (int j = 1; j <= numberOfAnswers; j++) {
                Answer answer = answers.get((long) j);
                answerList.add(answer);
                answerMultiplier++;
            }
            request.getSession().setAttribute("answers", answerList);
            request.getSession().setAttribute("answerMultiplier", answerMultiplier);
            request.getSession().setAttribute("questionNumber", 1);

            getServletContext().getRequestDispatcher("/display_quest.jsp").forward(request, response);
     //       break;
 //       }
//        System.out.println(questService.getQuestions());
//        System.out.println(questService.getAnswers());
//        System.out.println(questService.answersConnecting.values());
    }

}
