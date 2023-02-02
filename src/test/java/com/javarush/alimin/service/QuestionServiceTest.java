package com.javarush.alimin.service;

import com.javarush.alimin.entity.Answer;
import com.javarush.alimin.entity.Question;
import com.javarush.alimin.repository.AnswerRepository;
import com.javarush.alimin.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionService mockedQuestionService;

    private final QuestionRepository questionRepository = new QuestionRepository(new AnswerRepository());

    private Collection<Question> expectedQuestions = new ArrayList<>();

    private Question simpleQuestionForCreateMethod;

    @BeforeEach
    void setUp() {
        Collection<Answer> answers = List.of(
                new Answer(1L, 1L, "Answer1", 2L),
                new Answer(2L, 2L, "Answer2", 3L),
                new Answer(3L, 3L, "Answer3", 4L)
        );

        for (int i = 0; i < 3; i++) {
            questionRepository.create(new Question(0L, "Question" + (i + 1), answers));
        }

        expectedQuestions = questionRepository.getAll();

        simpleQuestionForCreateMethod = new Question(0L, "Question4", answers);
    }

    @Test
    public void getAllMethodTest() {
        Mockito.when(mockedQuestionService.getAll()).thenReturn(questionRepository.getAll());
        Collection<Question> actualQuestions = mockedQuestionService.getAll();
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedQuestions.size(), actualQuestions.size()),
                () -> Assertions.assertIterableEquals(expectedQuestions, actualQuestions),
                () -> Mockito.verify(mockedQuestionService, Mockito.times(1)).getAll()
        );
    }

    @ParameterizedTest
    @ValueSource(longs = 1)
    public void getMethodTest(long id) {
        Mockito.when(mockedQuestionService.get(id)).thenReturn(questionRepository.get(id));
        Question expectedQuestion = questionRepository.get(id);
        Question actualQuestion = mockedQuestionService.get(id);
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedQuestion, actualQuestion),
                () -> Mockito.verify(mockedQuestionService, Mockito.times(1)).get(id)
        );
    }

    @Test
    public void createMethodTest() {
        int expectedSize = questionRepository.getAll().size() + 1;
        Mockito.doAnswer(invocation -> {
            Object createdQuestion = invocation.getArgument(0);
            questionRepository.create((Question) createdQuestion);
            return null;
        }).when(mockedQuestionService).create(simpleQuestionForCreateMethod);

        mockedQuestionService.create(simpleQuestionForCreateMethod);

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedSize, questionRepository.getAll().size()),
                () -> Assertions.assertEquals(simpleQuestionForCreateMethod,
                        questionRepository.get(simpleQuestionForCreateMethod.getId())),
                () -> Mockito.verify(mockedQuestionService,
                        Mockito.times(1)).create(simpleQuestionForCreateMethod)
        );
    }
}