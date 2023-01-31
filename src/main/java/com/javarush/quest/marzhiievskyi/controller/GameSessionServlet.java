package com.javarush.quest.marzhiievskyi.controller;

import com.javarush.quest.marzhiievskyi.entity.Answer;
import com.javarush.quest.marzhiievskyi.entity.Quest;
import com.javarush.quest.marzhiievskyi.entity.Question;
import com.javarush.quest.marzhiievskyi.entity.User;
import com.javarush.quest.marzhiievskyi.service.GameSessionService;
import com.javarush.quest.marzhiievskyi.util.ServletConstants;
import com.javarush.quest.marzhiievskyi.util.ServletUtilMethod;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;


@WebServlet(name = ServletConstants.GAME_SESSION_SERVLET, value = ServletConstants.GAME_PATH)
public class GameSessionServlet extends HttpServlet {


    GameSessionService gameSessionService = GameSessionService.GAME_SESSION_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long questId = Long.valueOf(request.getParameter(ServletConstants.ID));
        Quest quest = gameSessionService.getQuest(questId);
        request.setAttribute(ServletConstants.QUEST, quest);

        Long nextQuestionId = (Long) request.getAttribute(ServletConstants.NEXT_QUESTION_ID);

        if (nextQuestionId == null) {
            nextQuestionId = quest.getStartQuestionId();
        }

        forwardToTheNextQuestion(request, response, questId, nextQuestionId);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long questId = Long.valueOf(request.getParameter(ServletConstants.ID));
        Quest quest = gameSessionService.getQuest(questId);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ServletConstants.USER);

        String nextQuestionIdFromAnswer = request.getParameter(ServletConstants.ANSWER);
        Long nextQuestionId = Long.parseLong(nextQuestionIdFromAnswer);

        gameSessionService.checkEndOfTheGame(user.getId(), nextQuestionId, questId);
        request.setAttribute(ServletConstants.QUEST, quest);
        request.setAttribute(ServletConstants.NEXT_QUESTION_ID, nextQuestionId);

        forwardToTheNextQuestion(request, response, questId, nextQuestionId);

    }

    private void forwardToTheNextQuestion(HttpServletRequest request, HttpServletResponse response, Long questId, Long nextQuestionId) throws ServletException, IOException {
        Optional<Question> question = gameSessionService.getQuestion(questId, nextQuestionId);
        question.ifPresent(q -> request.setAttribute(ServletConstants.QUESTION, q.getText()));
        Collection<Answer> answers = gameSessionService.getAnswers(questId, nextQuestionId);

        request.setAttribute(ServletConstants.ANSWERS, answers);
        request.setAttribute(ServletConstants.QUESTION_ID, nextQuestionId);

        ServletUtilMethod.forward(request, response, ServletConstants.WEB_INF_GAME_JSP);
    }
}
