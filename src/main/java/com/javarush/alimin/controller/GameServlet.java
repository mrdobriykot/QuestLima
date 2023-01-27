package com.javarush.alimin.controller;

import com.javarush.alimin.entity.Answer;
import com.javarush.alimin.entity.Question;
import com.javarush.alimin.service.QuestionService;
import com.javarush.alimin.util.Forward;
import com.javarush.alimin.util.URLPatterns;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Collection;
import java.util.Objects;

@WebServlet(name = "GameServlet", value = URLPatterns.GAME_SERVLET)
public class GameServlet extends HttpServlet {

    private final QuestionService questionService = QuestionService.QUESTION_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        setIpForSession(session, request);
        setPlayerNameForSession(session, request);
        setGameCounterForSession(session);
        setQuestionForRequest(request);
        Forward.forward(request, response, URLPatterns.GAME);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        setNextQuestionForRequest(request);
        Forward.forward(request, response, URLPatterns.GAME);

    }



    private void setNextQuestionForRequest(HttpServletRequest request) {
        Long answerId = Long.parseLong(request.getParameter("answerId"));
        Long nextQuestionId = findOutNextQuestionId(answerId);
        request.setAttribute("question", questionService.get(nextQuestionId));
    }


    private Long findOutNextQuestionId(Long answerId) {
        Collection<Question> questions = questionService.getAll();
        for (Question question : questions) {
            Collection<Answer> answers = question.getAnswers();
            if (Objects.nonNull(answers)) {
                for (Answer answer : answers) {
                    if (answer.getId().equals(answerId)) {
                       return answer.getNextQuestionId();
                    }
                }
            }
        }
        return 0L;
    }

    private void setIpForSession(HttpSession session, HttpServletRequest request) {
        String ipAddress = request.getLocalAddr();
        session.setAttribute("ipAddress", ipAddress);
    }

    private void setPlayerNameForSession(HttpSession session, HttpServletRequest request) {
        String name = request.getParameter("name");
        session.setAttribute("playerName", name);
    }

    private void setGameCounterForSession(HttpSession session) {
        Integer gameCounter = (Integer) session.getAttribute("gameCounter");
        if (Objects.isNull(gameCounter)) {
            gameCounter = 1;
            session.setAttribute("gameCounter", gameCounter);
        } else {
            session.setAttribute("gameCounter", gameCounter+1);
        }
    }

    private void setQuestionForRequest(HttpServletRequest request) {
        String currentQuestionId = request.getParameter("id");
        if (Objects.isNull(currentQuestionId)) {
            request.setAttribute("question", questionService.get(1L));
        } else {
            request.setAttribute("question", questionService.get(Long.parseLong(currentQuestionId)));
        }
    }
}
