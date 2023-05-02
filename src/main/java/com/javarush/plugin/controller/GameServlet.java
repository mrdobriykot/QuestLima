package com.javarush.plugin.controller;

import com.javarush.plugin.config.Config;
import com.javarush.plugin.entity.Answer;
import com.javarush.plugin.entity.Question;
import com.javarush.plugin.service.QuestionService;
import com.javarush.plugin.util.Util;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "GameServlet", value = "/game")
public class GameServlet extends HttpServlet {

    Collection<Question> questions = new ArrayList<>();
    Collection<Answer> answers = new ArrayList<>();
    Util util = new Util();

    QuestionService questionService = new QuestionService();

    @SneakyThrows
    public void init() {

        String fileSep = System.getProperty("file.separator");
        String dir = getServletContext().getRealPath("/");
        String questionFile = dir + "WEB-INF" + fileSep + "json" + fileSep + "questions.json";
        String answerFile = dir + "WEB-INF" + fileSep + "json" + fileSep + "answers.json";

        Config configParcer = new Config();
        questions = configParcer.jsonQuestionArrayParser(questionFile);
        answers = configParcer.jsonAnswerArrayParser(answerFile);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Question question = questionService.getQuestion(request, questions, answers);
        request.setAttribute("question", question);

        List<Answer> answerList = answers.stream()
                .filter(answer -> answer.getQuestionId() == question.getId())
                .collect(Collectors.toList());

        request.setAttribute("answerList", answerList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/game.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userAnswer = request.getParameter("userAnswer");
        System.out.println("DO POST: User Answer " + userAnswer);

        Long nextQuestionByAnswerId = util.findNextQuestionByAnswerId(userAnswer, answers);
        questionService.nextQuestionId = nextQuestionByAnswerId;

        response.sendRedirect(String.format("%s?id=%d", "/game", nextQuestionByAnswerId));

    }
}
