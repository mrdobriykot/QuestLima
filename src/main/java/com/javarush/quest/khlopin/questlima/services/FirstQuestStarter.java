package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.entity.game.Answer;
import com.javarush.quest.khlopin.questlima.entity.game.Quest;
import com.javarush.quest.khlopin.questlima.entity.game.Question;
import com.javarush.quest.khlopin.questlima.utills.DB;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;
import java.util.Collection;
import java.util.List;

public class FirstQuestStarter {
    //TODO в вынести в переменные класса и конструктор квест и сделать универсальным для любого квеста

    public Quest quest = null;
    private List<Question> questionList = null;

    private List<Answer> answerList;


    public void startQuest(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        quest = DB.questDataBase.get(id);


        questionList = quest.getQuestionList();

        Question question = questionList.get(0);


        answerList = question.getAnswerList();

        request.setAttribute("question1", question);
        request.setAttribute("answers", answerList);
    }

    public void nextStageOfQuest(HttpServletRequest request,String answer, int stage) {
        Question question = questionList.get(stage);


        if (answer.equals("false")) {
            String finishAnswer = answerList.get(1).getFinishAnswerText();
            request.setAttribute("lose",finishAnswer);
        } else {
            List<Answer> answerList = question.getAnswerList();
            request.setAttribute("question1", question);
            request.setAttribute("answers", answerList);
        }
    }

}
