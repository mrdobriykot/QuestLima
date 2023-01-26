package com.javarush.quest.marzhiievskyi.util.quests.test;


import com.javarush.quest.marzhiievskyi.entity.Answer;
import com.javarush.quest.marzhiievskyi.entity.GameState;
import com.javarush.quest.marzhiievskyi.entity.Quest;
import com.javarush.quest.marzhiievskyi.entity.Question;

import java.util.ArrayList;
import java.util.Collection;


public class DemoQuest extends Quest {



    private final Question firstQuestion = generateFirstQuestion();
    private final Question secondQuestion = generateSecondQuestion();
    private final Question lostQuestion = generateLostQuestion();
    private final Question anotherLost = generateAnotherLostQuestion();
    private final Question winQuestion = generateWinQuestion();

    public DemoQuest() {
        setName("Demo Quest");
        setStartingText("...!!!...");
        setStartQuestionId(firstQuestion.getId());
        generateQuestions();
    }


    private void generateQuestions() {
        Collection<Question> questionCollections = new ArrayList<>();
        questionCollections.add(firstQuestion);
        questionCollections.add(secondQuestion);
        questionCollections.add(lostQuestion);
        questionCollections.add(anotherLost);
        questionCollections.add(winQuestion);

        setQuestions(questionCollections);


    }

    private Question generateFirstQuestion() {

        Question firstQuestion = Question.builder()
                .id(1L)
                .questId(getId())
                .gameState(GameState.PLAY)
                .text("First question")
                .build();

        Answer agreeAnswer = Answer.builder()
                .id(1L)
                .questionId(firstQuestion.getId())
                .text("1 Agree answer")
                .nextQuestionId(2L)
                .build();

        Answer disagreeAnswer = Answer.builder()
                .id(2L)
                .questionId(firstQuestion.getId())
                .text("2 Disagree answer")
                .nextQuestionId(3L)
                .build();
        Answer answer = Answer.builder()
                .id(3L)
                .questionId(firstQuestion.getId())
                .text("3  answer")
                .nextQuestionId(3L)
                .build();

        Collection<Answer> answers = new ArrayList<>();
        answers.add(agreeAnswer);
        answers.add(disagreeAnswer);
        answers.add(answer);
        firstQuestion.setAnswerList(answers);

        return firstQuestion;
    }

    private Question generateSecondQuestion() {

        Question secondQuestion = Question.builder()
                .id(2L)
                .questId(getId())
                .gameState(GameState.PLAY)
                .text("Second question")
                .build();

        Answer agreeAnswer = Answer.builder()
                .id(4L)
                .questionId(secondQuestion.getId())
                .text("3 Agree answer")
                .nextQuestionId(4L)
                .build();

        Answer disagreeAnswer = Answer.builder()
                .id(5L)
                .questionId(secondQuestion.getId())
                .text("4 Disagree answer")
                .nextQuestionId(5L)
                .build();

        Collection<Answer> answers = new ArrayList<>();
        answers.add(agreeAnswer);
        answers.add(disagreeAnswer);
        secondQuestion.setAnswerList(answers);

        return secondQuestion;
    }

    private Question generateLostQuestion() {

        return Question.builder()
                .id(3L)
                .questId(getId())
                .gameState(GameState.LOST)
                .text("Lost question")
                .build();
    }

    private Question generateAnotherLostQuestion() {

        return Question.builder()
                .id(4L)
                .questId(getId())
                .gameState(GameState.LOST)
                .text("Lost question")
                .build();
    }

    private Question generateWinQuestion() {

        return Question.builder()
                .id(5L)
                .questId(getId())
                .gameState(GameState.WIN)
                .text("Win question")
                .build();
    }



}
