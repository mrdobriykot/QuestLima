package com.javarush.quest.marzhiievskyi.service;

import com.javarush.quest.marzhiievskyi.entity.Answer;
import com.javarush.quest.marzhiievskyi.entity.GameState;
import com.javarush.quest.marzhiievskyi.entity.Quest;
import com.javarush.quest.marzhiievskyi.entity.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GameSessionServiceTest {

    GameSessionService gameService = GameSessionService.GAME_SESSION_SERVICE;

    @Test
    void create() {
        gameService.create(new Quest());

        Quest quest = gameService.getQuest(1L);
        assertEquals(1, quest.getId());
    }

    @Test
    void getQuest() {
        Quest quest = gameService.getQuest(1L);
        assertEquals(1, quest.getId());
    }

    @Test
    void getAllQuests() {
        gameService.create(new Quest());
        Collection<Quest> allQuests = gameService.getAllQuests();
        assertEquals(2, allQuests.size());
    }

    @Test
    void getQuestion() {
        generateQuest();

        Optional<Question> optionalQuestion = gameService.getQuestion(3L, 2L);

        Question result = null;
        if (optionalQuestion.isPresent()) {
            result = optionalQuestion.get();
        }

        assert result != null;
        assertEquals(2L, result.getId());

    }



    @Test
    void getAnswers() {

        generateQuest();
        Collection<Answer> answers = gameService.getAnswers(3L, 1L);

        assertEquals(3, answers.size());

    }

    @Test
    void checkEndOfTheGame() {
        generateQuest();
        GameState lost = gameService.checkEndOfTheGame(1L, 1L, 3L);
        assertEquals(GameState.LOST, lost);

        GameState win = gameService.checkEndOfTheGame(1L, 2L, 3L);
        assertEquals(GameState.WIN, win);
    }

    private void generateQuest() {
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer());
        answers.add(new Answer());
        answers.add(new Answer());

        Question question = Question.builder()
                .id(1L)
                .answerList(answers)
                .gameState(GameState.LOST)
                .build();
        Question secondQuestion = Question.builder()
                .id(2L)
                .gameState(GameState.WIN)
                .build();

        ArrayList<Question> questions = new ArrayList<>();

        questions.add(question);
        questions.add(secondQuestion);

        Quest quest  = Quest.builder()
                .questions(questions)
                .build();
        gameService.create(quest);
    }
}