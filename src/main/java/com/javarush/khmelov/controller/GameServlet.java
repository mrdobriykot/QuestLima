package com.javarush.khmelov.controller;

import com.javarush.khmelov.config.Spring;
import com.javarush.khmelov.dto.GameTo;
import com.javarush.khmelov.dto.QuestionTo;
import com.javarush.khmelov.dto.UserTo;
import com.javarush.khmelov.service.GameService;
import com.javarush.khmelov.service.QuestionService;
import com.javarush.khmelov.util.Go;
import com.javarush.khmelov.util.Jsp;
import com.javarush.khmelov.util.Key;
import com.javarush.khmelov.util.Parser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(Go.GAME)
public class GameServlet extends HttpServlet {

    private final GameService gameService = Spring.getBean(GameService.class);
    private final QuestionService questionService = Spring.getBean(QuestionService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long questId = Long.parseLong(request.getParameter(Key.QUEST_ID));
        Optional<UserTo> user = Parser.getUser(request.getSession());
        if (user.isPresent()) {
            Long userId = user.get().getId();
            Optional<GameTo> game = gameService.getGame(questId, userId);
            if (game.isPresent()) {
                forwardToQuestion(request, response, game.get());
            } else {
                Jsp.redirect(request, response, Go.HOME, "Нет незавершенной игры");
            }
        } else {
            Jsp.forward(request, response, Go.LOGIN, "Сначала нужно войти в аккаунт");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long gameId = Parser.getId(request);
        Long answerId = Parser.getId(request, Key.ANSWER);
        Optional<GameTo> game = gameService.checkAnswer(gameId, answerId);
        if (game.isPresent()) {
            GameTo gameTo = game.get();
            if (answerId == 0) {
                request.setAttribute(Key.ERROR_MESSAGE, "Нужно выбрать какой-то ответ");
            }
            forwardToQuestion(request, response, gameTo);
        } else {
            Jsp.forward(request, response, Go.HOME, "Нет такой игры");
        }
    }

    private void forwardToQuestion(HttpServletRequest request, HttpServletResponse response, GameTo game)
            throws ServletException, IOException {
        request.setAttribute(Key.GAME, game);
        Optional<QuestionTo> question = questionService.get(game.getCurrentQuestionId());
        request.setAttribute(Key.QUESTION, question.orElseThrow());
        Jsp.forward(request, response, Go.GAME);
    }
}