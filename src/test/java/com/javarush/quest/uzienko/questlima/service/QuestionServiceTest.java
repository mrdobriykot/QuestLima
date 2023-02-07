package com.javarush.quest.uzienko.questlima.service;

import com.javarush.quest.uzienko.questlima.config.Winter;
import com.javarush.quest.uzienko.questlima.entity.QuestState;
import com.javarush.quest.uzienko.questlima.entity.Question;
import com.javarush.quest.uzienko.questlima.repository.QuestMapRepository;
import com.javarush.quest.uzienko.questlima.repository.QuestionMapRepository;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("OptionalGetWithoutIsPresent")
class QuestionServiceTest {

    @Test
    void createTest() {
        try (MockedStatic<Winter> winter = Mockito.mockStatic(Winter.class)) {
            //Arrange
            winter.when(() -> Winter.getBean(QuestionMapRepository.class)).thenReturn(new QuestMapRepository());
            QuestionService questionService = new QuestionService();
            Collection<Question> questionCollection = questionService.getAll();
            assertTrue(questionCollection.isEmpty());

            //Act
            questionService.create(new Question(0L, "testText", QuestState.PLAY));

            //Assert
            assertEquals(questionCollection.size(), 1);
        }
    }

    @Test
    void updateTest() {
        try (MockedStatic<Winter> winter = Mockito.mockStatic(Winter.class)) {
            //Arrange
            winter.when(() -> Winter.getBean(QuestionMapRepository.class)).thenReturn(new QuestMapRepository());
            QuestionService questionService = new QuestionService();
            Collection<Question> questionCollection = questionService.getAll();
            assertTrue(questionCollection.isEmpty());

            Question question = new Question(0L, "testText", QuestState.PLAY);
            questionService.create(question);
            assertEquals("testText", questionService.get(question.getId()).get().getText());

            Question questionNew = new Question();
            questionNew.setId(question.getId());
            questionNew.setText("testNewText");

            //Act
            questionService.update(questionNew);

            //Assert
            assertEquals("testNewText", questionService.get(question.getId()).get().getText());
        }
    }

    @Test
    void deleteTest() {
        try (MockedStatic<Winter> winter = Mockito.mockStatic(Winter.class)) {
            //Arrange
            winter.when(() -> Winter.getBean(QuestionMapRepository.class)).thenReturn(new QuestMapRepository());
            QuestionService questionService = new QuestionService();
            Collection<Question> questionCollection = questionService.getAll();
            assertTrue(questionCollection.isEmpty());

            Question question = new Question(0L, "testText", QuestState.PLAY);
            questionService.create(question);
            assertEquals("testText", questionService.get(question.getId()).get().getText());

            //Act
            questionService.delete(question);

            //Assert
            assertEquals(questionCollection.size(), 0);
        }
    }

    @Test
    void getAllTest() {
        try (MockedStatic<Winter> winter = Mockito.mockStatic(Winter.class)) {
            //Arrange
            winter.when(() -> Winter.getBean(QuestionMapRepository.class)).thenReturn(new QuestMapRepository());
            QuestionService questionService = new QuestionService();
            Collection<Question> questionCollection = questionService.getAll();
            assertTrue(questionCollection.isEmpty());

            Question question0 = new Question(0L, "testText0", QuestState.PLAY);
            Question question1 = new Question(0L, "testText1", QuestState.WIN);
            Question question2 = new Question(0L, "testText2", QuestState.LOSS);
            questionService.create(question0);
            questionService.create(question1);
            questionService.create(question2);

            //Act
            Collection<Question> all = questionService.getAll();

            //Assert
            assertEquals(3, all.size());
            assertTrue(all.stream()
                    .anyMatch(question0::equals));
            assertTrue(all.stream()
                    .anyMatch(question1::equals));
            assertTrue(all.stream()
                    .anyMatch(question2::equals));
        }
    }

    @Test
    void getTest() {
        try (MockedStatic<Winter> winter = Mockito.mockStatic(Winter.class)) {
            //Arrange
            winter.when(() -> Winter.getBean(QuestionMapRepository.class)).thenReturn(new QuestMapRepository());
            QuestionService questionService = new QuestionService();
            Collection<Question> questionCollection = questionService.getAll();
            assertTrue(questionCollection.isEmpty());

            Question question0 = new Question(0L, "testText0", QuestState.PLAY);
            questionService.create(question0);

            //Act
            Question result = questionService.get(question0.getId()).get();

            //Assert
            assertEquals(question0, result);
        }
    }
}