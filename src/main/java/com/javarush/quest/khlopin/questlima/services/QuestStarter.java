package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.entity.game.*;
import com.javarush.quest.khlopin.questlima.entity.user.User;
import com.javarush.quest.khlopin.questlima.utills.DB;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

import java.util.List;

public class QuestStarter {
    //TODO в вынести в переменные класса и создать конструктор квест и сделать универсальным для любого квеста
    private Game game;
    @Getter
    private Quest quest;
    private List<Question> questionList;

    private List<Answer> answerList;
    private Question question;
    @Getter
    private String finishTrueAnswer;


    public void startQuest(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        long id = Long.parseLong(request.getParameter("id"));
        quest = DB.questDataBase.get(id);
        questionList = quest.getQuestionList();
        question = questionList.get(0);
        answerList = question.getAnswerList();
        game = new Game(quest, GameState.PLAY);
        user.getGameList().add(game);
        request.setAttribute("question", question);
        request.setAttribute("answers", answerList);
    }

    public void nextStageOfQuest(HttpServletRequest request, String answer, int stage) {
        Question currentQuestion = questionList.get(stage);
        question = currentQuestion;
        answerList = currentQuestion.getAnswerList();
        checkWin(request, answer, currentQuestion);

    }

    public void checkWin(HttpServletRequest request, String answer, Question currentQuestion) {

        if (answer.equals("false")) {
            game.setGameState(GameState.LOSE);
            String finishAnswer = answerList.get(1).getFinishAnswerText();
            request.setAttribute("result", finishAnswer);
        } else if (answer.equals("true")) {
            request.setAttribute("question", currentQuestion);
            request.setAttribute("answers", answerList);

        }
    }

    public void findTrueAnswer() {
        game.setGameState(GameState.WIN);
        finishTrueAnswer = question.getAnswerList().get(0).getFinishAnswerText();
    }


}

