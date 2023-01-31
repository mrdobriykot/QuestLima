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
        setName(TextDemoQuest.NAME_QUEST_TEST);
        setStartingText(TextDemoQuest.STARTING_TEXT_QUEST);
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
                .text(TextDemoQuest.STARTING_QUESTION_TEXT)
                .build();

        Answer agreeAnswer = Answer.builder()
                .id(1L)
                .questionId(firstQuestion.getId())
                .text(TextDemoQuest.AGREE_ANSWER)
                .nextQuestionId(2L)
                .build();

        Answer disagreeAnswer = Answer.builder()
                .id(2L)
                .questionId(firstQuestion.getId())
                .text(TextDemoQuest.DISAGREE_ANSWER)
                .nextQuestionId(3L)
                .build();

        Collection<Answer> answers = new ArrayList<>();
        answers.add(agreeAnswer);
        answers.add(disagreeAnswer);
        firstQuestion.setAnswerList(answers);

        return firstQuestion;
    }

    private Question generateSecondQuestion() {

        Question secondQuestion = Question.builder()
                .id(2L)
                .questId(getId())
                .gameState(GameState.PLAY)
                .text(TextDemoQuest.SECOND_QUESTION_TEXT)
                .build();

        Answer agreeAnswer = Answer.builder()
                .id(3L)
                .questionId(secondQuestion.getId())
                .text(TextDemoQuest.AGREE_ANSWER)
                .nextQuestionId(4L)
                .build();

        Answer disagreeAnswer = Answer.builder()
                .id(4L)
                .questionId(secondQuestion.getId())
                .text(TextDemoQuest.DISAGREE_ANSWER)
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
                .text(TextDemoQuest.LOST_GAME_TEXT)
                .build();
    }

    private Question generateAnotherLostQuestion() {

        return Question.builder()
                .id(4L)
                .questId(getId())
                .gameState(GameState.LOST)
                .text(TextDemoQuest.LOST_GAME_TEXT)
                .build();
    }

    private Question generateWinQuestion() {

        return Question.builder()
                .id(5L)
                .questId(getId())
                .gameState(GameState.WIN)
                .text(TextDemoQuest.WIN_GAME_TEXT)
                .build();
    }



}
