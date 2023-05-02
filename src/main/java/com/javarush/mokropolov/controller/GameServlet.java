package com.javarush.mokropolov.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Optional;

import com.javarush.mokropolov.config.Winter;
import com.javarush.mokropolov.entity.Game;
import com.javarush.mokropolov.entity.Question;
import com.javarush.mokropolov.entity.User;
import com.javarush.mokropolov.service.GameService;
import com.javarush.mokropolov.service.QuestionService;
import com.javarush.mokropolov.util.Go;
import com.javarush.mokropolov.util.Jsp;
import com.javarush.mokropolov.util.Key;
import com.javarush.mokropolov.util.Parser;

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
                Jsp.redirect(request, response, Go.HOME, "Вы ни во что не играете в данный момент");
            }
        } else {
            Jsp.forward(request, response, Go.LOGIN, "Пройдите аутентификацию."); 
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
                request.setAttribute(Key.ERROR_MESSAGE, "Выберите ответ на вопрос");
            }
            forwardToQuestion(request, response, gameDto);
        } else {
            Jsp.forward(request, response, Go.HOME, "Игра не существует");
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
