package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.entity.game.Answer;
import com.javarush.quest.khlopin.questlima.entity.game.Question;
import com.javarush.quest.khlopin.questlima.utills.DB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class QuestionRepository implements Repository<Question> {

    private final HashMap<Long,Question> questionsMap = new HashMap<>();
    private static final AtomicLong questionId = new AtomicLong(0);
    private static final AtomicLong questionsId = new AtomicLong(0);

    private static final Logger log = LogManager.getLogger(QuestionRepository.class);

    public QuestionRepository() {

        questionsMap.put(questionsId.incrementAndGet(),create(2L, 1,"Ты потерял память. Принять вызов НЛО?", DB.answerDataBase.get(1L)));
               questionsMap.put(questionsId.incrementAndGet(),create(3L, 1,"Ты принял вызов, поднимешься на мостик к капитану?",DB.answerDataBase.get(2L)));
                questionsMap.put(questionsId.incrementAndGet(),create(null, 1, "Ты поднялся на мостик. Ты кто?", DB.answerDataBase.get(3L)));
        log.trace("Question repository was uploaded");
    }

    public Question create(Long nextQuestionId, long questId, String value, List<Answer> answerList) {
        long idForQuest = questionId.incrementAndGet();
        return new Question(idForQuest, questId, nextQuestionId, value, answerList);
    }




    @Override
    public Question get(long id) {
        return questionsMap.get(id);
    }

    @Override
    public void delete(long id) {
        questionsMap.remove(id);
    }

    @Override
    public void update(long idOfOlderEntity, Question question) {
        delete(idOfOlderEntity);
        questionsMap.put(idOfOlderEntity,question);
    }

    @Override
    public List<Question> getAll() {
        return new ArrayList<>(questionsMap.values());
    }


}
