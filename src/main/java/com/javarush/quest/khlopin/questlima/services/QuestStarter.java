package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.entity.game.*;
import com.javarush.quest.khlopin.questlima.entity.user.User;
import com.javarush.quest.khlopin.questlima.utills.Constants;
import com.javarush.quest.khlopin.questlima.utills.DB;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
@Getter
public class QuestStarter {

    //TODO вынести в переменные класса и создать конструктор квест и сделать универсальным для любого квеста
    private Game game;
    private Quest quest;
    private List<Question> questionList;

    private List<Answer> answerList;
    private Question question;
    private String finishTrueAnswer;

    private User user;
    private static final Logger log = LogManager.getLogger(QuestStarter.class);


    public void startQuest(HttpServletRequest request) {
        user = (User) request.getSession().getAttribute(Constants.USER);
        long id = Long.parseLong(request.getParameter("id"));
        quest = DB.questDataBase.get(id);
        questionList = quest.getQuestionList();
        question = questionList.get(0);
        answerList = question.getAnswerList();
        game = new Game(quest, GameState.PLAY);
        user.getGameList().add(game);
        request.setAttribute(Constants.QUESTION, question);
        request.setAttribute(Constants.ANSWERS, answerList);
        log.info("Была создана игра" + user + "для пользователя " + user);
    }

    public void nextStageOfQuest(HttpServletRequest request, String answer, int stage) {
        log.trace(user + " перешел на стадию" + stage + "в игре" + game);
        Question currentQuestion = questionList.get(stage);
        question = currentQuestion;
        answerList = currentQuestion.getAnswerList();
        checkWin(request, answer, currentQuestion);

    }

    public void checkWin(HttpServletRequest request, String answer, Question currentQuestion) {

        if (answer.equals(Constants.FALSE)) {
            game.setGameState(GameState.LOSE);
            String finishAnswer = answerList.get(1).getFinishAnswerText();
            request.setAttribute(Constants.RESULT, finishAnswer);
        } else if (answer.equals(Constants.TRUE)) {
            request.setAttribute(Constants.QUESTION, currentQuestion);
            request.setAttribute(Constants.ANSWERS, answerList);

        }
    }

    public void findTrueAnswer() {
        game.setGameState(GameState.WIN);
        finishTrueAnswer = question.getAnswerList().get(0).getFinishAnswerText();
        if (finishTrueAnswer != null) {
        log.trace("найден правильный ответ в игре " + game + "ответ: " + finishTrueAnswer); }
        else {
            log.trace(" правильный ответ для игры " + game + " не найден " + "пользователь " + user);
        }
    }


}

