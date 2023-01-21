package com.javarush.quest.marzhiievskyi.resource.epicspacequest;


import com.javarush.quest.marzhiievskyi.entity.*;
import com.javarush.quest.marzhiievskyi.service.AnswerService;
import com.javarush.quest.marzhiievskyi.service.QuestService;
import com.javarush.quest.marzhiievskyi.service.QuestionService;
import com.javarush.quest.marzhiievskyi.service.UserService;

import java.util.Optional;


public class TestQuest {
    static QuestService questService = QuestService.QUEST_SERVICE;
    static QuestionService questionService = QuestionService.QUESTION_SERVICE;
    static AnswerService answerService = AnswerService.ANSWER_SERVICE;
    static UserService userService = UserService.USER_SERVICE;


    private static void createQuestContentInRepo(Long id) {


        Question firstQuestion = Question.builder()
                .id(-1L)
                .gameState(GameState.PLAY)
                .text("First question")
                .questId(id)
                .build();

        Question secondQuestion = Question.builder()
                .id(-1L)
                .gameState(GameState.PLAY)
                .text("Second question")
                .questId(id)
                .build();

        Question winQuestion = Question.builder()
                .id(-1L)
                .gameState(GameState.WIN)
                .text("Win question")
                .questId(id)
                .build();

        Question lostQuestion = Question.builder()
                .id(-1L)
                .gameState(GameState.LOST)
                .text("Lost question")
                .questId(id)
                .build();


        Answer agreeAnswer = Answer.builder()
                .id(-1L)
                .text("Agree")
                .build();

        Answer disagreeAnswer = Answer.builder()
                .id(-1L)
                .text("Disagree")
                .build();


        answerService.create(agreeAnswer);
        answerService.create(disagreeAnswer);
        questionService.create(firstQuestion);
        questionService.create(secondQuestion);
        questionService.create(winQuestion);
        questionService.create(lostQuestion);
    }

    public static void main(String[] args) {

        Quest testQuest = questService.get(2L);
        createQuestContentInRepo(testQuest.getId());

        Optional<Question> firstOptional = questionService.get("First question");
        if (firstOptional.isPresent()){
            Question first = firstOptional.get();
        }

    }


}
