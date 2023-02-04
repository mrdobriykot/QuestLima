package com.javarush.dobrov.controller;

import com.javarush.dobrov.entity.Answer;
import com.javarush.dobrov.entity.Question;
import com.javarush.dobrov.service.QuestService;
import com.javarush.dobrov.util.SingleEntity;
import com.sun.jdi.IntegerType;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DisplayAnswersServlet", value = "/DisplayAnswersServlet")
public class DisplayAnswersServlet extends HttpServlet {

    private final QuestService questService = SingleEntity.getInstance(QuestService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getServletContext().getRequestDispatcher("/display_quest.jsp").forward(request, response);

        int questionIdCount = 0;

        Map<Long, Question> questions = questService.getQuestions();
        Map<Long, Answer> answers = questService.getAnswers();
        List<Answer> answerList = new ArrayList<>();
        Integer answerMultiplier1 = (Integer) request.getSession().getAttribute("answerMultiplier");
        Integer questionNumber = (Integer) request.getSession().getAttribute("questionNumber");
        System.out.println("number_"+questionNumber);

        Map<Integer, Integer> answersConnecting = questService.answersConnecting;

        for (int i = 1+ questionNumber; i <= questions.size(); i++) {

            request.setAttribute("question", questions.get((long) i).getQuestionText());

            Integer numberOfAnswers = answersConnecting.get(i);
            System.out.println("numberAnswers " + numberOfAnswers);
            for (int j = 1 + answerMultiplier1; j <= numberOfAnswers + answerMultiplier1; j++) {
                Answer answer = answers.get((long) j);
                answerList.add(answer);
                questionIdCount++;
            }
            answerMultiplier1 += questionIdCount;
            request.getSession().setAttribute("answers", answerList);
            request.getSession().setAttribute("answerMultiplier", answerMultiplier1);
            request.getSession().setAttribute("questionNumber", questionNumber+1);
            System.out.println("----------------------");

            getServletContext().getRequestDispatcher("/display_quest.jsp").forward(request, response);
            break;

        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

}
}