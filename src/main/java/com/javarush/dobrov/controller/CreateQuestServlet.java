package com.javarush.dobrov.controller;

import com.javarush.dobrov.entity.Answer;
import com.javarush.dobrov.entity.Question;
import com.javarush.dobrov.repository.QuestRepository;
import com.javarush.dobrov.service.QuestService;
import com.javarush.dobrov.service.QuestionService;
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

        questService.createQuest(quest_header, quest_text);



        Map<Long, Question> questions = questService.getQuestions();
        Map<Long, Answer> answers = questService.getAnswers();
        List<Answer> answerList = new ArrayList<>();
        request.setAttribute("quest_header", quest_header);

        for (int i = 1; i < questions.size(); i++) {
            request.setAttribute("question", questions.get((long) i).getQuestionText());
            int numberOfAnswers = getAnswers(i);

            for (int j = 1; j <= numberOfAnswers; j++) {
                Answer answer = answers.get((long) j);
                answerList.add(answer);
            }
            request.setAttribute("answers", answerList);
            getServletContext().getRequestDispatcher("/display_quest.jsp").forward(request, response);

        }
//        System.out.println(questService.getQuestions());
//        System.out.println(questService.getAnswers());
//        System.out.println(questService.answersConnecting.values());
    }
    private int getAnswers(int questionNumber) {
        Map<Integer, Integer> answersConnecting = questService.answersConnecting;
        return answersConnecting.get(questionNumber);
    }

}
