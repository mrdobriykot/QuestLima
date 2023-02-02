package com.javarush.alimin.repository;

import com.javarush.alimin.entity.Answer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

class AnswerRepositoryTest {

    private final AnswerRepository actualAnswers = new AnswerRepository();
    private final Map<Long,Answer> expectedAnswers = new HashMap<>();
    private Answer simpleAnswerForCreateAndUpdate;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 3; i++) {
            actualAnswers.create(new Answer(0L, i+1L, "Answer" + (i+1), i+2L));
            expectedAnswers.put(i+1L, new Answer(i+1L, i+1L, "Answer" + (i+1), i+2L));
        }
        simpleAnswerForCreateAndUpdate = new Answer(0L, 3L, "Answer4", 5L);
    }

    @Test
    public void whenInvokeGetAllMethod_ThenAnswerCollectionWithExpectedContentAndSizeReturn() {
        int expectedSize = expectedAnswers.size();
        Assertions.assertAll(
                () -> Assertions.assertIterableEquals(expectedAnswers.values(), actualAnswers.getAll()),
                () -> Assertions.assertEquals(expectedSize, actualAnswers.getAll().size())
        );
    }

    @ParameterizedTest
    @ValueSource(longs = 1)
    public void whenInvokeGetMethodWithIdParameter_ThenAnswerWithAppropriateIdReturn(long id) {
        Answer expectedAnswer = expectedAnswers.get(id);
        Assertions.assertEquals(expectedAnswer, actualAnswers.get(id));
    }

    @Test
    public void whenInvokeCreateMethod_ThenAnswerFromParameterAddedToAnswerRepositoryAndSizeIncrements() {
        int expectedSize = actualAnswers.getAll().size() + 1;
        actualAnswers.create(simpleAnswerForCreateAndUpdate);
        Assertions.assertAll(
                () -> Assertions.assertEquals(simpleAnswerForCreateAndUpdate,
                        actualAnswers.get(simpleAnswerForCreateAndUpdate.getId())),
                () -> Assertions.assertEquals(expectedSize, actualAnswers.getAll().size())
        );
    }

    @Test
    public void whenInvokeUpdateMethod_ThenAnswerFromParameterReplacesAnswerWithSameIdInAnswerRepositorySizeStaysSame() {
        int expectedSize = actualAnswers.getAll().size();
        Answer replacedAnswer = actualAnswers.get(2L);
        simpleAnswerForCreateAndUpdate.setId(2L);
        actualAnswers.update(simpleAnswerForCreateAndUpdate);
        Assertions.assertAll(
                () -> Assertions.assertEquals(simpleAnswerForCreateAndUpdate,
                        actualAnswers.get(simpleAnswerForCreateAndUpdate.getId())),
                () -> Assertions.assertEquals(expectedSize, actualAnswers.getAll().size()),
                () -> Assertions.assertFalse(actualAnswers.getAll().contains(replacedAnswer))
        );
    }
}