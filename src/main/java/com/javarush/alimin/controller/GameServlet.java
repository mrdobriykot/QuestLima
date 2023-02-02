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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Objects;

@WebServlet(name = "GameServlet", value = URLPatterns.GAME_SERVLET)
public class GameServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(GameServlet.class);

    private final QuestionService questionService = QuestionService.QUESTION_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Setting HttpSession attributes:");
        HttpSession session = request.getSession();
        setIpForSession(session, request);
        setPlayerNameForSession(session, request);
        setGameCounterForSession(session);
        log.debug("Setting HttpRequest attribute");
        setQuestionForRequest(request);
        Forward.forward(request, response, URLPatterns.GAME);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        log.debug("Setting next question for HttpRequest");
        setNextQuestionForRequest(request);
        Forward.forward(request, response, URLPatterns.GAME);

    }



    private void setNextQuestionForRequest(HttpServletRequest request) {
        log.debug("Parsing answerId");
        Long answerId = Long.parseLong(request.getParameter("answerId"));
        Long nextQuestionId = findOutNextQuestionId(answerId);
        request.setAttribute("question", questionService.get(nextQuestionId));
    }


    private Long findOutNextQuestionId(Long answerId) {
        log.debug("Finding out next question id");
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
        log.debug("Setting IP");
        String ipAddress = request.getLocalAddr();
        session.setAttribute("ipAddress", ipAddress);
        log.debug("IP set");
    }

    private void setPlayerNameForSession(HttpSession session, HttpServletRequest request) {
        log.debug("Setting player name");
        String name = request.getParameter("name");
        if (Objects.isNull(name) || StringUtils.isEmpty(name)) {
            name = "a mysterious stranger";
        }
        session.setAttribute("playerName", name);
        log.debug("Player name set");
    }

    private void setGameCounterForSession(HttpSession session) {
        log.debug("Setting game counter");
        Integer gameCounter = (Integer) session.getAttribute("gameCounter");
        if (Objects.isNull(gameCounter)) {
            gameCounter = 1;
            session.setAttribute("gameCounter", gameCounter);
        } else {
            session.setAttribute("gameCounter", gameCounter+1);
        }
        log.debug("Game counter set");
    }

    private void setQuestionForRequest(HttpServletRequest request) {
        log.debug("Setting question to HttpRequest in doGet()");
        String currentQuestionId = request.getParameter("id");
        if (Objects.isNull(currentQuestionId)) {
            request.setAttribute("question", questionService.get(1L));
        } else {
            request.setAttribute("question", questionService.get(Long.parseLong(currentQuestionId)));
        }
    }
}
