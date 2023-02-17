package com.javarush.alimin.repository;

import com.javarush.alimin.entity.Answer;
import com.javarush.alimin.entity.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class QuestionRepositoryTest {
    private final QuestionRepository actualQuestions = new QuestionRepository (new AnswerRepository());
    private final Map<Long, Question> expectedQuestions = new HashMap<>();
    private Question simpleQuestionForCreateAndUpdate;

    @BeforeEach
    void setUp() {

        Collection<Answer> answers = List.of(
                new Answer(1L, 1L, "Answer1", 2L),
                new Answer(2L, 2L, "Answer2", 3L),
                new Answer(3L, 3L, "Answer3", 4L)
        );

        for (int i = 0; i < 3; i++) {
            actualQuestions.create(new Question(0L, "Question" + (i+1), answers));
            expectedQuestions.put(i+1L, new Question(i+1L, "Question" + (i+1), answers));
        }

        simpleQuestionForCreateAndUpdate = new Question(0L, "Question4", answers);
    }

    @Test
    public void whenInvokeGetAllMethod_ThenQuestionCollectionWithExpectedContentAndSizeReturn() {
        int expectedSize = expectedQuestions.size();
        Assertions.assertAll(
                () -> Assertions.assertIterableEquals(expectedQuestions.values(), actualQuestions.getAll()),
                () -> Assertions.assertEquals(expectedSize, actualQuestions.getAll().size())
        );
    }

    @ParameterizedTest
    @ValueSource(longs = 1)
    public void whenInvokeGetMethodWithIdParameter_ThenQuestionWithAppropriateIdReturn(long id) {
        Question expectedQuestion = expectedQuestions.get(id);
        Assertions.assertEquals(expectedQuestion, actualQuestions.get(id));
    }

    @Test
    public void whenInvokeCreateMethod_ThenQuestionFromParameterAddedToQuestionRepositoryAndSizeIncrements() {
        int expectedSize = actualQuestions.getAll().size() + 1;
        actualQuestions.create(simpleQuestionForCreateAndUpdate);
        Assertions.assertAll(
                () -> Assertions.assertEquals(simpleQuestionForCreateAndUpdate,
                        actualQuestions.get(simpleQuestionForCreateAndUpdate.getId())),
                () -> Assertions.assertEquals(expectedSize, actualQuestions.getAll().size())
        );
    }

    @Test
    public void whenInvokeUpdateMethod_ThenQuestionFromParameterReplacesQuestionWithSameIdInQuestionRepositorySizeStaysSame() {
        int expectedSize = actualQuestions.getAll().size();
        Question replacedQuestion = actualQuestions.get(2L);
        simpleQuestionForCreateAndUpdate.setId(2L);
        actualQuestions.update(simpleQuestionForCreateAndUpdate);
        Assertions.assertAll(
                () -> Assertions.assertEquals(simpleQuestionForCreateAndUpdate,
                        actualQuestions.get(simpleQuestionForCreateAndUpdate.getId())),
                () -> Assertions.assertEquals(expectedSize, actualQuestions.getAll().size()),
                () -> Assertions.assertFalse(actualQuestions.getAll().contains(replacedQuestion))
        );
    }
}