package com.javarush.quest.shubchynskyi.questlima.controllers.quest_controllers;

import com.javarush.quest.shubchynskyi.questlima.entity.game.Answer;
import com.javarush.quest.shubchynskyi.questlima.entity.game.Quest;
import com.javarush.quest.shubchynskyi.questlima.entity.game.Question;
import com.javarush.quest.shubchynskyi.questlima.service.AnswerService;
import com.javarush.quest.shubchynskyi.questlima.service.ImageService;
import com.javarush.quest.shubchynskyi.questlima.service.QuestService;
import com.javarush.quest.shubchynskyi.questlima.service.QuestionService;
import com.javarush.quest.shubchynskyi.questlima.util.Go;
import com.javarush.quest.shubchynskyi.questlima.util.Jsp;
import com.javarush.quest.shubchynskyi.questlima.util.Key;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "QuestEditServlet", value = Go.QUEST_EDIT)
@MultipartConfig(fileSizeThreshold = 1 << 20)
public class QuestEditServlet extends HttpServlet {

    private final QuestService questService = QuestService.QUEST_SERVICE;
    private final QuestionService questionService = QuestionService.QUESTION_SERVICE;
    private final AnswerService answerService = AnswerService.ANSWER_SERVICE;
    private final ImageService imageService = ImageService.IMAGE_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questId = request.getParameter(Key.ID);
        if (questService.get(questId).isPresent()) {
            request.setAttribute(Key.QUEST, questService.get(questId).get());
        }
        Jsp.forward(request, response, Go.QUEST_EDIT);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.containsKey(Key.QUEST_NAME)) {
            questEdit(request, response);
        } else if (parameterMap.containsKey(Key.QUESTION_ID)) {
            questionEdit(request, response);
        } else {
            Jsp.forward(request, response, Go.QUESTS_LIST);
        }
    }

    private void questEdit(HttpServletRequest request, HttpServletResponse response) {
        if (questService.get(request.getParameter(Key.ID)).isPresent()) {
            Quest quest = questService.get(request.getParameter(Key.ID)).get();
            quest.setName(request.getParameter(Key.QUEST_NAME));
            quest.setDescription(request.getParameter(Key.QUEST_DESCRIPTION));
            questService.update(quest);
            Jsp.redirect(response, Key.ID_URI_PATTERN.formatted(Go.QUEST_EDIT, request.getParameter(Key.ID)));
        }
    }

    private void questionEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId = request.getParameter(Key.QUESTION_ID);
        if (questionService.get(questionId).isPresent()) {
            Question question = questionService.get(questionId).get();
            imageService.uploadImage(request, question.getImage());
            question.setText(request.getParameter(Key.QUESTION_TEXT));
            questionService.update(question);
            for (Answer answer : question.getAnswers()) {
                String answerText = request.getParameter(String.valueOf(answer.getId()));
                answer.setText(answerText);
                answerService.update(answer);
            }
            Jsp.redirect(response,
                    Key.ID_URI_PATTERN.formatted(Go.QUEST_EDIT, request.getParameter(Key.ID))
                            + Key.LABEL_URI_PATTERN + question.getId());
        }
    }
}
