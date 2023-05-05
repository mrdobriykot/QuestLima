package com.javarush.khmelov.controller.cmd.impl;

import com.javarush.khmelov.controller.cmd.Command;
import com.javarush.khmelov.view.View;
import com.javarush.khmelov.dto.GameTo;
import com.javarush.khmelov.dto.QuestionTo;
import com.javarush.khmelov.dto.UserTo;
import com.javarush.khmelov.service.GameService;
import com.javarush.khmelov.service.QuestionService;
import com.javarush.khmelov.view.Go;
import com.javarush.khmelov.controller.Key;
import com.javarush.khmelov.controller.Parser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Optional;

@Controller(Go.GAME)
@AllArgsConstructor
public class GameCommand implements Command {

    private final GameService gameService;
    private final QuestionService questionService;

    @Override
    public View get(HttpServletRequest request) throws ServletException, IOException {
        Long questId = Long.parseLong(request.getParameter(Key.QUEST_ID));
        Optional<UserTo> user = Parser.getUser(request.getSession());
        if (user.isPresent()) {
            Long userId = user.get().getId();
            return gameService.getGame(questId, userId)
                    .map(gameTo -> forwardToQuestion(request, gameTo))
                    .orElseGet(() -> View.redirect(Go.HOME, "Нет незавершенной игры"));
        } else {
            return View.forward(Go.LOGIN, "Сначала нужно войти в аккаунт");
        }
    }

    @Override
    public View post(HttpServletRequest request) throws ServletException, IOException {
        Long gameId = Parser.getId(request);
        Long answerId = Parser.getId(request, Key.ANSWER);
        Optional<GameTo> game = gameService.checkAnswer(gameId, answerId);
        if (game.isPresent()) {
            GameTo gameTo = game.get();
            if (answerId == 0) {
                request.setAttribute(Key.ERROR_MESSAGE, "Нужно выбрать какой-то ответ");
            }
            return forwardToQuestion(request, gameTo);
        } else {
            return View.forward(Go.HOME, "Нет такой игры");
        }
    }

    private View forwardToQuestion(HttpServletRequest request, GameTo game) {
        request.setAttribute(Key.GAME, game);
        Optional<QuestionTo> question = questionService.get(game.getCurrentQuestionId());
        request.setAttribute(Key.QUESTION, question.orElseThrow());
        return View.forward(Go.GAME);
    }
}
