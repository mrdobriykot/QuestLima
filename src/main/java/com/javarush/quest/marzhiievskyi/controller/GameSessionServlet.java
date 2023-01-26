package com.javarush.quest.marzhiievskyi.controller;

import com.javarush.quest.marzhiievskyi.entity.Answer;
import com.javarush.quest.marzhiievskyi.entity.Quest;
import com.javarush.quest.marzhiievskyi.entity.Question;
import com.javarush.quest.marzhiievskyi.service.GameSessionService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

//Finally its f***ing quests works =/

@WebServlet(name = "GameSessionServlet", value = "/game")
public class GameSessionServlet extends HttpServlet {
    GameSessionService gameSessionService = GameSessionService.GAME_SESSION_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long questId = Long.valueOf(request.getParameter("id"));
        //HttpSession session = request.getSession();
        //User user = (User) session.getAttribute("user");

        //TODO add some info about user and game session

        Quest quest = gameSessionService.getQuest(questId);
        request.setAttribute("quest", quest);

        Long nextQuestionId = (Long) request.getAttribute("nextQuestionId");

        if (nextQuestionId == null) {
            nextQuestionId = quest.getStartQuestionId();
        }


        forwardToTheNextQuestion(request, response, questId, nextQuestionId);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long questId = Long.valueOf(request.getParameter("id"));
        Quest quest = gameSessionService.getQuest(questId);

        String nextQuestionIdFromAnswer = request.getParameter("answer");
        Long nextQuestionId = Long.parseLong(nextQuestionIdFromAnswer);
        request.setAttribute("quest", quest);
        request.setAttribute("nextQuestionId", nextQuestionId);
        forwardToTheNextQuestion(request, response, questId, nextQuestionId);

    }

    private void forwardToTheNextQuestion(HttpServletRequest request, HttpServletResponse response, Long questId, Long nextQuestionId) throws ServletException, IOException {
        Optional<Question> question = gameSessionService.getQuestion(questId, nextQuestionId);

        question.ifPresent(q -> request.setAttribute("question", q.getText()));

        Collection<Answer> answers = gameSessionService.getAnswers(questId, nextQuestionId);
        request.setAttribute("answers", answers);
        request.setAttribute("questionId", nextQuestionId);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/game.jsp");
        requestDispatcher.forward(request, response);
    }
}
