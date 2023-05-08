package com.javarush.khmelov.controller.cmd.impl;

import com.javarush.khmelov.controller.Key;
import com.javarush.khmelov.controller.Parser;
import com.javarush.khmelov.controller.cmd.Command;
import com.javarush.khmelov.dto.QuestTo;
import com.javarush.khmelov.dto.QuestionTo;
import com.javarush.khmelov.dto.UserTo;
import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.service.ImageService;
import com.javarush.khmelov.service.QuestService;
import com.javarush.khmelov.service.QuestionService;
import com.javarush.khmelov.view.Go;
import com.javarush.khmelov.view.View;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Optional;

import static com.javarush.khmelov.controller.Key.QUEST;


@Controller(Go.QUEST)
@AllArgsConstructor
public class QuestCommand implements Command {

    private final QuestService questService;
    private final QuestionService questionService;
    private final ImageService imageService;

    @Override
    public View get(HttpServletRequest req) throws ServletException, IOException {
        long id = Parser.getId(req);
        Optional<QuestTo> quest = questService.get(id);
        req.setAttribute(QUEST, quest.orElseThrow());
        return View.forward(Go.QUEST);
    }

    @Override
    public View post(HttpServletRequest req) throws IOException, ServletException {
        Optional<UserTo> editor = Parser.getUser(req.getSession());
        if (editor.isPresent() && editor.get().getRole() == Role.ADMIN) {
            Long id = Parser.getId(req);
            Long questionId = Parser.getId(req, "questionId");
            String text = req.getParameter(Key.TEXT);
            Optional<QuestionTo> question = questionService.update(questionId,text);
            if (question.isPresent()) {
                imageService.uploadImage(req, question.get().getImage());
            }
            String uri = "%s?id=%d#bookmark%d".formatted(Go.QUEST, id, questionId);
            return View.redirect(uri);
        } else {
            return View.redirect(Go.QUEST, "Недостаточно прав для редактирования");
        }
    }
}
