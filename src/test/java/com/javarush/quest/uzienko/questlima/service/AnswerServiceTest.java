package com.javarush.quest.uzienko.questlima.service;

import com.javarush.quest.uzienko.questlima.config.Winter;
import com.javarush.quest.uzienko.questlima.entity.Answer;
import com.javarush.quest.uzienko.questlima.repository.AnswerMapRepository;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("ALL")
class AnswerServiceTest {

    @Test
    void createTest() {
        try (MockedStatic<Winter> winter = Mockito.mockStatic(Winter.class)) {
            //Arrange
            winter.when(() -> Winter.getBean(AnswerMapRepository.class)).thenReturn(new AnswerMapRepository());
            AnswerService answerService = new AnswerService();
            Collection<Answer> answerCollection = answerService.getAll();
            assertTrue(answerCollection.isEmpty());

            //Act
            answerService.create(new Answer(0L, "testTest", 0L));

            //Assert
            assertEquals(answerCollection.size(), 1);
        }
    }

    @Test
    void updateTest() {
        try (MockedStatic<Winter> winter = Mockito.mockStatic(Winter.class)) {
            //Arrange
            winter.when(() -> Winter.getBean(AnswerMapRepository.class)).thenReturn(new AnswerMapRepository());
            AnswerService answerService = new AnswerService();
            Collection<Answer> answerCollection = answerService.getAll();
            assertTrue(answerCollection.isEmpty());

            Answer answer = new Answer(0L, "testText", 0L);
            answerService.create(answer);
            assertEquals("testText", answerService.get(answer.getId()).get().getText());

            Answer answerNew = new Answer();
            answerNew.setId(answer.getId());
            answerNew.setText("testNewText");

            //Act
            answerService.update(answerNew);

            //Assert
            assertEquals("testNewText", answerService.get(answer.getId()).get().getText());
        }
    }

    @Test
    void deleteTest() {
        try (MockedStatic<Winter> winter = Mockito.mockStatic(Winter.class)) {
            //Arrange
            winter.when(() -> Winter.getBean(AnswerMapRepository.class)).thenReturn(new AnswerMapRepository());
            AnswerService answerService = new AnswerService();
            Collection<Answer> answerCollection = answerService.getAll();
            assertTrue(answerCollection.isEmpty());

            Answer answer = new Answer(0L, "testText", 0L);
            answerService.create(answer);
            assertEquals("testText", answerService.get(answer.getId()).get().getText());

            //Act
            answerService.delete(answer);

            //Assert
            assertEquals(answerCollection.size(), 0);
        }
    }

    @Test
    void getAllTest() {
        try (MockedStatic<Winter> winter = Mockito.mockStatic(Winter.class)) {
            //Arrange
            winter.when(() -> Winter.getBean(AnswerMapRepository.class)).thenReturn(new AnswerMapRepository());
            AnswerService answerService = new AnswerService();
            Collection<Answer> answerCollection = answerService.getAll();
            assertTrue(answerCollection.isEmpty());

            Answer answer0 = new Answer(0L, "testText0", 0L);
            Answer answer1 = new Answer(1L, "testText1", 1L);
            Answer answer2 = new Answer(2L, "testText2", 2L);
            answerService.create(answer0);
            answerService.create(answer1);
            answerService.create(answer2);

            //Act
            Collection<Answer> all = answerService.getAll();

            //Assert
            assertEquals(3, all.size());
            assertTrue(all.stream()
                    .anyMatch(answer0::equals));
            assertTrue(all.stream()
                    .anyMatch(answer1::equals));
            assertTrue(all.stream()
                    .anyMatch(answer2::equals));
        }
    }

    @Test
    void getTest() {
        try (MockedStatic<Winter> winter = Mockito.mockStatic(Winter.class)) {
            //Arrange
            winter.when(() -> Winter.getBean(AnswerMapRepository.class)).thenReturn(new AnswerMapRepository());
            AnswerService answerService = new AnswerService();
            Collection<Answer> answerCollection = answerService.getAll();
            assertTrue(answerCollection.isEmpty());

            Answer answer0 = new Answer(0L, "testText0", 0L);
            answerService.create(answer0);

            //Act
            Answer result = answerService.get(answer0.getId()).get();

            //Assert
            assertEquals(answer0, result);
        }
    }

    @Test
    void getAllByQuestionIdTest() {
        try (MockedStatic<Winter> winter = Mockito.mockStatic(Winter.class)) {
            //Arrange
            winter.when(() -> Winter.getBean(AnswerMapRepository.class)).thenReturn(new AnswerMapRepository());
            AnswerService answerService = new AnswerService();
            Collection<Answer> answerCollection = answerService.getAll();
            assertTrue(answerCollection.isEmpty());

            Answer answer0 = new Answer(0L, "testText0", 0L);
            Answer answer1 = new Answer(0L, "testText1", 1L);
            Answer answer2 = new Answer(2L, "testText2", 2L);
            answerService.create(answer0);
            answerService.create(answer1);
            answerService.create(answer2);

            //Act
            Stream<Answer> allStream = answerService.getAllByQuestionId(0L);
            List<Answer> answerList = allStream.toList();

            //Assert
            assertEquals(2, answerList.size());
            assertEquals(2,
                    answerList.stream().filter(e -> answer0.getQuestionId().equals(e.getQuestionId()))
                            .count());
        }
    }
}