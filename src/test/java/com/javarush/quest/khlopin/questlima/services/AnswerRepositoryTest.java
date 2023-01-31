package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.entity.game.Answer;
import com.javarush.quest.khlopin.questlima.entity.game.AnswerState;
import com.javarush.quest.khlopin.questlima.entity.user.Role;
import com.javarush.quest.khlopin.questlima.entity.user.User;
import com.javarush.quest.khlopin.questlima.utills.DB;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
class AnswerRepositoryTest {



    @ParameterizedTest
    @CsvSource("test, 1, TRUE")
    void create(String text, Long questionId, AnswerState answerState) {
        Answer answer = DB.answerDataBase.create(text, questionId, answerState);
        assertEquals(new Answer(answer.getAnswerId(),text,questionId,answerState),answer);
    }

    @ParameterizedTest
    @CsvSource("test, 1, TRUE, Finish Text")
    void createFinishAnswer(String text,Long questionId,AnswerState answerState, String finishText) {
        Answer answer = DB.answerDataBase.createFinishAnswer(text, questionId, answerState, finishText);
        assertEquals(new Answer(answer.getAnswerId(),text,questionId,answerState,finishText),answer);
    }
}