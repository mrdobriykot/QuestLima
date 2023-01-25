package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.entity.Repository;
import com.javarush.quest.khlopin.questlima.entity.game.Answer;
import com.javarush.quest.khlopin.questlima.entity.game.Question;
import com.javarush.quest.khlopin.questlima.utills.DB;


import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class QuestionRepository implements Repository<Question> {

    private final HashMap<Long,Question> questionsMap = new HashMap<>();
    private static final AtomicLong questionId = new AtomicLong(0);
    private static final AtomicLong questionsId = new AtomicLong(0);

    public QuestionRepository() {

        questionsMap.put(questionsId.incrementAndGet(),create(2L,"Ты потерял память. Принять вызов НЛО?", DB.answerDataBase.get(1L)));
               questionsMap.put(questionsId.incrementAndGet(),create(3L,"Ты принял вызов, поднимешься на мостик к капитану?",DB.answerDataBase.get(2L)));
                questionsMap.put(questionsId.incrementAndGet(),create(null, "Ты поднялся на мостик. Ты кто?", DB.answerDataBase.get(3L)));
    }

    public Question create(Long nextQuestionId, String value, List<Answer> answerList) {
        long idForQuest = questionId.incrementAndGet();
        return new Question(idForQuest, nextQuestionId, value, answerList);
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

    @Override
    public Optional<Question> find(String login) {
        return Optional.empty();
    } //заглушка

    //    @Override
//    public Optional<Question> find(String text) {
//        for (Map.Entry<Long, Question> questionEntry : questionsMap.entrySet()) {
//            Question question = questionEntry.getValue();
//            String questionText = question.getText();
//            if (questionText.equals(text)) {
//                return Optional.of(question);
//            }
//        }
//        return Optional.empty();
//    } //TODO Исправить!
}
