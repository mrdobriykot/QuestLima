package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.entity.game.Answer;
import com.javarush.quest.khlopin.questlima.entity.game.AnswerState;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class AnswerRepository implements Repository<List<Answer>> {


    private final HashMap<Long, List<Answer>> answersMap = new HashMap<>();

    private static final AtomicLong answerId = new AtomicLong(0);


    public Answer create(String text, Long questionId, AnswerState answerState) {
        long idForAnswer = answerId.incrementAndGet();

        return new Answer(
                idForAnswer,
                text,
                questionId,
                answerState
        );
    }

    public Answer createFinishAnswer(String text, Long questionId, AnswerState answerState, String finishAnswer) {
        long idForAnswer = answerId.incrementAndGet();

        return new Answer(
                idForAnswer,
                text,
                questionId,
                answerState,
                finishAnswer
        );
    }

    @Override
    public List<Answer> get(long id) {
        return answersMap.get(id);
    }

    @Override
    public void delete(long id) {
        answersMap.remove(id);
    }

    @Override
    public void update(long idOfOlderEntity, List<Answer> entity) {
        delete(idOfOlderEntity);
        answersMap.put(idOfOlderEntity, entity);
    }

    @Override
    public Collection<List<Answer>> getAll() {
        return answersMap.values();
    }
    public Optional<Answer> find(String answer) {
        for (Map.Entry<Long, List<Answer>> longListEntry : answersMap.entrySet()) {
            List<Answer> value = longListEntry.getValue();
            for (Answer answerFromDB : value) {
                String text = answerFromDB.getText();
                if (text.equals(answer)) {
                    return Optional.of(answerFromDB);
                }
            }
        }
        return Optional.empty();
    }

}
