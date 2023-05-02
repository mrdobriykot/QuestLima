package com.javarush.mokropolov.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Optional;

import com.javarush.mokropolov.config.Winter;
import com.javarush.mokropolov.entity.Quest;
import com.javarush.mokropolov.entity.Question;
import com.javarush.mokropolov.entity.Role;
import com.javarush.mokropolov.entity.User;
import com.javarush.mokropolov.service.ImageService;
import com.javarush.mokropolov.service.QuestService;
import com.javarush.mokropolov.service.QuestionService; 
import com.javarush.mokropolov.util.Go;
import com.javarush.mokropolov.util.Jsp;
import com.javarush.mokropolov.util.Key;
import com.javarush.mokropolov.util.Parser;
import static com.javarush.mokropolov.util.Key.QUEST;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(value = {Go.QUEST}, name = "QuestServlet")
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
