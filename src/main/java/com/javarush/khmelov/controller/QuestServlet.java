package com.javarush.khmelov.controller;

import com.javarush.khmelov.config.Winter;
import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.service.ImageService;
import com.javarush.khmelov.service.QuestService;
import com.javarush.khmelov.service.QuestionService;
import com.javarush.khmelov.util.Go;
import com.javarush.khmelov.util.Jsp;
import com.javarush.khmelov.util.Key;
import com.javarush.khmelov.util.Parser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

import static com.javarush.khmelov.util.Key.QUEST;


@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(Go.QUEST)
public class QuestServlet extends HttpServlet {

    private final QuestService questService = Winter.getBean(QuestService.class);
    private final QuestionService questionService = Winter.getBean(QuestionService.class);
    private final ImageService imageService = Winter.getBean(ImageService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Parser.getId(req);
        Optional<Quest> quest = questService.get(id);
        req.setAttribute(QUEST, quest.orElseThrow());
        Jsp.forward(req, resp, Go.QUEST);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Optional<User> editor = Parser.getUser(req.getSession());
        if (editor.isPresent() && editor.get().getRole() == Role.ADMIN) {
            Long id = Parser.getId(req);
            Long questionId = Parser.getId(req, "questionId");
            String text = req.getParameter(Key.TEXT);
            Optional<Question> question = questionService.update(questionId,text);
            if (question.isPresent()) {
                imageService.uploadImage(req, question.get().getImage());
            }
            String uri = "%s?id=%d#bookmark%d".formatted(Go.QUEST, id, questionId);
            Jsp.redirect(resp, uri);
        } else {
            Jsp.forward(req, resp, Go.QUEST, "Недостаточно прав для редактирования");
        }
    }
}
