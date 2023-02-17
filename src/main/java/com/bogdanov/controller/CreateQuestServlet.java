package com.bogdanov.controller;

import com.bogdanov.entity.Answer;
import com.bogdanov.entity.Quest;
import com.bogdanov.entity.Question;
import com.bogdanov.service.*;
import com.bogdanov.util.Go;
import com.bogdanov.util.Jsp;
import com.bogdanov.util.Key;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


@WebServlet(name = "CreateQuestServlet", value = Go.CREATE_QUEST)
@MultipartConfig(fileSizeThreshold = 1<<20)
public class CreateQuestServlet extends HttpServlet {



    QuestService serviceQuest = QuestService.QUEST_SERVICE;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParametr = request.getParameter(Key.ID);
        if (Objects.nonNull(idParametr)) {
            long id;

            try {
                id = Long.parseLong(idParametr);
                Optional<Quest> quest = serviceQuest.getQuestFromRep(id);
                //request.setAttribute("questions", quest.get().getQuestions());
                quest.ifPresent(value -> request.setAttribute(Key.QUESTS, value));
                Jsp.forward(request, response, Go.CREATE_QUEST);

            } catch (Exception e) {
                request.setAttribute(Key.QUESTS, serviceQuest.getAll());
                Jsp.forward(request, response, Go.CREATE_QUEST);
            }


        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Quest questCunstructor = Jsp.getQuestCunstructor(request);
        Collection<Answer> answerCunstructor = Jsp.getAnswerCunstructor(request);
        Collection<Question> questionCunstructor = Jsp.getQuestionCunstructor(request);

        if(parameterMap.containsKey("create")){
            serviceQuest.create(questCunstructor, questionCunstructor,answerCunstructor);

        } else if (parameterMap.containsKey("update")) {
            serviceQuest.update(questCunstructor, questionCunstructor,answerCunstructor);
        } else if (parameterMap.containsKey("delete")) {
            serviceQuest.delete(questCunstructor);
        }else throw new IllegalArgumentException("unknown command");

        //imageService.uploadImage(request,user.getImage());

        Jsp.response(response,Go.USERS);


    }




}
