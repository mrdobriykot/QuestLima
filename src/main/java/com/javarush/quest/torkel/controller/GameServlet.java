package com.javarush.quest.torkel.controller;

import com.javarush.quest.torkel.config.Winter;
import com.javarush.quest.torkel.entity.Game;
import com.javarush.quest.torkel.entity.Question;
import com.javarush.quest.torkel.entity.User;
import com.javarush.quest.torkel.service.GameService;
import com.javarush.quest.torkel.service.QuestionService;
import com.javarush.quest.torkel.util.Go;
import com.javarush.quest.torkel.util.Jsp;
import com.javarush.quest.torkel.util.Key;
import com.javarush.quest.torkel.util.Parser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(value = Go.GAME, name = "GameServlet")
public class GameServlet extends HttpServlet {

    private final GameService gameService = Winter.getBean(GameService.class);
    private final QuestionService questionService = Winter.getBean(QuestionService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long questId = Long.parseLong(request.getParameter(Key.QUEST_ID));
        Optional<User> user = Parser.getUser(request.getSession());
        if (user.isPresent()) {
            Long userId = user.get().getId();
            Optional<Game> game = gameService.getGame(questId, userId);
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
        Optional<Game> game = gameService.checkAnswer(gameId, answerId);
        if (game.isPresent()) {
            Game gameDto = game.get();
            if (answerId == 0) {
                request.setAttribute(Key.ERROR_MESSAGE, "Нужно выбрать какой-то ответ");
            }
            forwardToQuestion(request, response, gameDto);
        } else {
            Jsp.forward(request, response, Go.HOME, "Нет такой игры");
        }
    }

    private void forwardToQuestion(HttpServletRequest request, HttpServletResponse response, Game game)
            throws ServletException, IOException {
        request.setAttribute(Key.GAME, game);
        Optional<Question> question = questionService.get(game.getCurrentQuestionId());
        request.setAttribute(Key.QUESTION, question.orElseThrow());
        Jsp.forward(request, response, Go.GAME);
    }
}