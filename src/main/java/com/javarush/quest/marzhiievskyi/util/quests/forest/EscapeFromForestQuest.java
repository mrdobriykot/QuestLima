package com.javarush.quest.marzhiievskyi.util.quests.forest;

import com.javarush.quest.marzhiievskyi.entity.Answer;
import com.javarush.quest.marzhiievskyi.entity.GameState;
import com.javarush.quest.marzhiievskyi.entity.Quest;
import com.javarush.quest.marzhiievskyi.entity.Question;

import java.util.ArrayList;
import java.util.Collection;

public class EscapeFromForestQuest extends Quest {

    private final Question firstQuestion = firstQuestion();
    private final Question question2 = questionId2();
    private final Question question3 = questionId3();
    private final Question question4 = questionId4();
    private final Question question5 = questionId5();
    private final Question question6 = questionId6();
    private final Question question7 = questionId7();
    private final Question question8 = questionId8();
    private final Question question9 = questionId9();
    private final Question question10 = questionId10();
    private final Question question11 = questionId11();
    private final Question question12 = questionId12();
    private final Question question13 = questionId13();
    private final Question question14 = questionId14();
    private final Question question15 = questionId15();
    private final Question question16 = questionId16();
    private final Question question17 = questionId17();
    private final Question question18 = questionId18();
    private final Question question19 = questionId19();
    private final Question question20 = questionId20();
    private final Question question21 = questionId21();
    private final Question question22 = questionId22();


    public EscapeFromForestQuest() {
        setName(TextEscapeFromForest.NAME_QUEST);
        setStartingText(TextEscapeFromForest.STARTING_TEXT);
        setStartQuestionId(firstQuestion().getId());
        generateQuestions();

    }

    private void generateQuestions() {
        Collection<Question> questionCollections = new ArrayList<>();

        questionCollections.add(firstQuestion);
        questionCollections.add(question2);
        questionCollections.add(question3);
        questionCollections.add(question4);
        questionCollections.add(question5);
        questionCollections.add(question6);
        questionCollections.add(question7);
        questionCollections.add(question8);
        questionCollections.add(question9);
        questionCollections.add(question10);
        questionCollections.add(question11);
        questionCollections.add(question12);
        questionCollections.add(question13);
        questionCollections.add(question14);
        questionCollections.add(question15);
        questionCollections.add(question16);
        questionCollections.add(question17);
        questionCollections.add(question18);
        questionCollections.add(question19);
        questionCollections.add(question20);
        questionCollections.add(question21);
        questionCollections.add(question22);


        setQuestions(questionCollections);
    }

    private Question firstQuestion() {
        Question question1 = Question.builder()
                .id(1L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_1)
                .gameState(GameState.PLAY)
                .build();

        Answer answer1 = Answer.builder()
                .id(1L)
                .questionId(question1.getId())
                .text(TextEscapeFromForest.ANSWER_ID_1)
                .nextQuestionId(2L)
                .build();

        Answer answer2 = Answer.builder()
                .id(2L)
                .questionId(question1.getId())
                .text(TextEscapeFromForest.ANSWER_ID_2)
                .nextQuestionId(3L)
                .build();
        Answer answer3 = Answer.builder()
                .id(3L)
                .questionId(question1.getId())
                .text(TextEscapeFromForest.ANSWER_ID_3)
                .nextQuestionId(4L)
                .build();

        Collection<Answer> answers = new ArrayList<>();

        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);

        question1.setAnswerList(answers);

        return question1;

    }

    private Question questionId2() {
        Question question2 = Question.builder()
                .id(2L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_2)
                .gameState(GameState.PLAY)
                .build();

        Answer answer1 = Answer.builder()
                .id(4L)
                .questionId(question2.getId())
                .text(TextEscapeFromForest.ANSWER_ID_4)
                .nextQuestionId(5L)
                .build();

        Answer answer2 = Answer.builder()
                .id(5L)
                .questionId(question2.getId())
                .text(TextEscapeFromForest.ANSWER_ID_5)
                .nextQuestionId(6L)
                .build();
        Answer answer3 = Answer.builder()
                .id(6L)
                .questionId(question2.getId())
                .text(TextEscapeFromForest.ANSWER_ID_6)
                .nextQuestionId(7L)
                .build();

        Collection<Answer> answers = new ArrayList<>();

        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);

        question2.setAnswerList(answers);

        return question2;

    }

    private Question questionId3() {
        Question question3 = Question.builder()
                .id(3L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_3)
                .gameState(GameState.PLAY)
                .build();

        Answer answer1 = Answer.builder()
                .id(7L)
                .questionId(question3.getId())
                .text(TextEscapeFromForest.ANSWER_ID_7)
                .nextQuestionId(8L)
                .build();

        Answer answer2 = Answer.builder()
                .id(8L)
                .questionId(question3.getId())
                .text(TextEscapeFromForest.ANSWER_ID_8)
                .nextQuestionId(9L)
                .build();
        Answer answer3 = Answer.builder()
                .id(9L)
                .questionId(question3.getId())
                .text(TextEscapeFromForest.ANSWER_ID_9)
                .nextQuestionId(10L)
                .build();

        Collection<Answer> answers = new ArrayList<>();

        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);

        question3.setAnswerList(answers);

        return question3;

    }

    private Question questionId4() {
        Question question4 = Question.builder()
                .id(4L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_4)
                .gameState(GameState.PLAY)
                .build();

        Answer answer1 = Answer.builder()
                .id(10L)
                .questionId(question4.getId())
                .text(TextEscapeFromForest.ANSWER_ID_10)
                .nextQuestionId(11L)
                .build();

        Answer answer2 = Answer.builder()
                .id(11L)
                .questionId(question4.getId())
                .text(TextEscapeFromForest.ANSWER_ID_11)
                .nextQuestionId(12L)
                .build();
        Answer answer3 = Answer.builder()
                .id(12L)
                .questionId(question4.getId())
                .text(TextEscapeFromForest.ANSWER_ID_12)
                .nextQuestionId(13L)
                .build();

        Collection<Answer> answers = new ArrayList<>();

        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);

        question4.setAnswerList(answers);

        return question4;

    }

    private Question questionId5() {

        return Question.builder()
                .id(5L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_5)
                .gameState(GameState.LOST)
                .build();

    }

    private Question questionId6() {
        Question question6 = Question.builder()
                .id(6L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_6)
                .gameState(GameState.PLAY)
                .build();

        Answer answer1 = Answer.builder()
                .id(13L)
                .questionId(question6.getId())
                .text(TextEscapeFromForest.ANSWER_ID_13)
                .nextQuestionId(14L)
                .build();

        Answer answer2 = Answer.builder()
                .id(14L)
                .questionId(question6.getId())
                .text(TextEscapeFromForest.ANSWER_ID_14)
                .nextQuestionId(15L)
                .build();
        Answer answer3 = Answer.builder()
                .id(15L)
                .questionId(question6.getId())
                .text(TextEscapeFromForest.ANSWER_ID_15)
                .nextQuestionId(16L)
                .build();

        Collection<Answer> answers = new ArrayList<>();

        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);

        question6.setAnswerList(answers);

        return question6;

    }

    private Question questionId7() {

        return Question.builder()
                .id(7L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_7)
                .gameState(GameState.LOST)
                .build();

    }
    private Question questionId8() {

        return Question.builder()
                .id(8L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_8)
                .gameState(GameState.LOST)
                .build();

    }

    private Question questionId9() {
        Question question9 = Question.builder()
                .id(9L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_9)
                .gameState(GameState.PLAY)
                .build();

        Answer answer1 = Answer.builder()
                .id(16L)
                .questionId(question9.getId())
                .text(TextEscapeFromForest.ANSWER_ID_16)
                .nextQuestionId(17L)
                .build();

        Answer answer2 = Answer.builder()
                .id(17L)
                .questionId(question9.getId())
                .text(TextEscapeFromForest.ANSWER_ID_17)
                .nextQuestionId(18L)
                .build();
        Answer answer3 = Answer.builder()
                .id(18L)
                .questionId(question9.getId())
                .text(TextEscapeFromForest.ANSWER_ID_18)
                .nextQuestionId(19L)
                .build();

        Collection<Answer> answers = new ArrayList<>();

        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);

        question9.setAnswerList(answers);

        return question9;

    }

    private Question questionId10() {

        return Question.builder()
                .id(10L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_10)
                .gameState(GameState.LOST)
                .build();

    }

    private Question questionId11() {
        Question question11 = Question.builder()
                .id(11L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_11)
                .gameState(GameState.PLAY)
                .build();

        Answer answer1 = Answer.builder()
                .id(19L)
                .questionId(question11.getId())
                .text(TextEscapeFromForest.ANSWER_ID_19)
                .nextQuestionId(20L)
                .build();

        Answer answer2 = Answer.builder()
                .id(20L)
                .questionId(question11.getId())
                .text(TextEscapeFromForest.ANSWER_ID_20)
                .nextQuestionId(21L)
                .build();
        Answer answer3 = Answer.builder()
                .id(21L)
                .questionId(question11.getId())
                .text(TextEscapeFromForest.ANSWER_ID_21)
                .nextQuestionId(22L)
                .build();

        Collection<Answer> answers = new ArrayList<>();

        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);

        question11.setAnswerList(answers);

        return question11;

    }


    private Question questionId12() {

        return Question.builder()
                .id(12L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_12)
                .gameState(GameState.LOST)
                .build();
    }

    private Question questionId13() {

        return Question.builder()
                .id(13L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_13)
                .gameState(GameState.LOST)
                .build();

    }
    private Question questionId14() {

        return Question.builder()
                .id(14L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_14)
                .gameState(GameState.LOST)
                .build();

    }
    private Question questionId15() {

        return Question.builder()
                .id(15L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_15)
                .gameState(GameState.LOST)
                .build();

    }

    private Question questionId16() {

        return Question.builder()
                .id(16L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_16)
                .gameState(GameState.WIN)
                .build();

    }

    private Question questionId17() {

        return Question.builder()
                .id(17L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_17)
                .gameState(GameState.LOST)
                .build();

    }

    private Question questionId18() {

        return Question.builder()
                .id(18L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_18)
                .gameState(GameState.WIN)
                .build();

    }

    private Question questionId19() {

        return Question.builder()
                .id(19L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_19)
                .gameState(GameState.LOST)
                .build();

    }

    private Question questionId20() {

        return Question.builder()
                .id(20L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_20)
                .gameState(GameState.LOST)
                .build();

    }
    private Question questionId21() {

        return Question.builder()
                .id(21L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_21)
                .gameState(GameState.WIN)
                .build();

    }

    private Question questionId22() {

        return Question.builder()
                .id(22L)
                .questId(getId())
                .text(TextEscapeFromForest.QUESTION_ID_22)
                .gameState(GameState.LOST)
                .build();

    }

}
