package sheff.module3.game.rpgquest.repository;

import sheff.module3.game.rpgquest.entity.Answer;
import sheff.module3.game.rpgquest.entity.Question;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class AnswerRepoTest {

    @Test
    void getAnswersByQuestionId() {
        int questionId = 2;
        List<Answer> answers = new ArrayList<>();

        IntStream.rangeClosed(1, 3)
                .forEach((num) -> answers.add(new Answer(
                        num, "text" + num, new Question(
                        num, "text" + num, Question.Type.CONTINUE), Mockito.mock(Question.class)))
                );

        AnswerRepo answerRepo = new AnswerRepo();
        for (Answer answer : answers) {
            answerRepo.save(answer.getId(), answer);
        }

        List<Answer> expected = answers.stream()
                .filter(v -> v.getPrev().getId() == questionId)
                .toList();

        List<Answer> actual = answerRepo.getAnswersByQuestionId(questionId);

        assertEquals(expected, actual);
    }
}
