package com.javarush.quest.uzienko.questlima.controller;

import com.javarush.quest.uzienko.questlima.config.UrlConfig;
import com.javarush.quest.uzienko.questlima.config.Winter;
import com.javarush.quest.uzienko.questlima.entity.Answer;
import com.javarush.quest.uzienko.questlima.entity.Question;
import com.javarush.quest.uzienko.questlima.service.AnswerService;
import com.javarush.quest.uzienko.questlima.service.QuestionService;
import com.javarush.quest.uzienko.questlima.service.StatisticService;
import com.javarush.quest.uzienko.questlima.utils.Jsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "QuestionPage", value = UrlConfig.ENDPOINT_QUESTION_PAGE)
public class QuestionPageServlet extends HttpServlet {

    private final QuestionService questionService = Winter.getBean(QuestionService.class);
    private final AnswerService answerService = Winter.getBean(AnswerService.class);
    private final StatisticService statisticService = Winter.getBean(StatisticService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Question question = getQuestion(req);
        if (question != null) {
            req.setAttribute("question", question);
            req.setAttribute("statistic", statisticService.getStatistic(req.getSession()));
            switch (question.getQuestState()) {
                case LOSS -> Jsp.include(req, resp, UrlConfig.VIEW_FOR_QUESTION_LOST_PAGE_SERVLET);
                case WIN -> Jsp.include(req, resp, UrlConfig.VIEW_FOR_QUESTION_WIN_PAGE_SERVLET);
                case PLAY -> {
                    List<Answer> answerList = answerService.getAllByQuestionId(question.getId()).toList();
                    req.setAttribute("answerList", answerList);
                    Jsp.include(req, resp, UrlConfig.VIEW_FOR_QUESTION_PLAY_PAGE_SERVLET);
                }
                default -> Jsp.forward(req, resp, UrlConfig.ENDPOINT_ERROR_404);
            }
        } else {
            Jsp.forward(req, resp, UrlConfig.ENDPOINT_ERROR_404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Answer answer = getAnswer(req);
        if (answer != null) {
            Jsp.redirect(req, resp,
                    String.format("%s?id=%d", UrlConfig.ENDPOINT_QUESTION_PAGE, answer.getNextQuestionId()));
        } else {
            Jsp.forward(req, resp, UrlConfig.ENDPOINT_ERROR_404);
        }
    }

    private Answer getAnswer(HttpServletRequest req) {
        return Optional.ofNullable(req.getParameter("userAnswer"))
                .map(Long::valueOf)
                .flatMap(answerService::get)
                .orElse(null);
    }

    private Question getQuestion(HttpServletRequest req) {
        return Optional.ofNullable(req.getParameter("id"))
                .map(Long::valueOf)
                .flatMap(questionService::get)
                .orElse(null);
    }
}
