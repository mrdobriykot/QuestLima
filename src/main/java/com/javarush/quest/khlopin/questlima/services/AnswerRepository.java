package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.entity.game.Answer;
import com.javarush.quest.khlopin.questlima.entity.game.AnswerState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class AnswerRepository implements Repository<List<Answer>> {


    private final HashMap<Long, List<Answer>> answersMap = new HashMap<>();

    private static final AtomicLong answerId = new AtomicLong(0);
    private static final AtomicLong answersId = new AtomicLong(0);
    private static final Logger log = LogManager.getLogger(AnswerRepository.class);

    public AnswerRepository() {
        answersMap.put(answersId.incrementAndGet(), List.of(
                create("Принять вызов", 1L,AnswerState.TRUE),
               createFinishAnswer("Отклонить вызов", 1L,AnswerState.FALSE,"Ты отклонил вызов. Поражение.")));

        answersMap.put(answersId.incrementAndGet(), List.of(
                create("Подняться на мостик",2L,AnswerState.TRUE),
                createFinishAnswer("Отказаться подниматься на мостик", 2L,AnswerState.FALSE,"Ты не пошел на переговоры. Поражение.")));

        answersMap.put(answersId.incrementAndGet(),List.of(
                createFinishAnswer("Рассказать правду о себе", 3L,AnswerState.TRUE,"Ты победил."),
                createFinishAnswer("Солгать о себе",3L, AnswerState.FALSE,"Твою ложь разоблочили. Поражение.")));

        log.trace("Репозиторий с ответами был загружен");
    }



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

}
