package com.javarush.khmelov.entity;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest extends BaseTest{

    public static final long PRIMARY_KEY = 1L;

    @Test
    void getId() {
        User user = session.get(User.class, ID);
        System.out.println(user);
        //bidirectional OneToMany
        Collection<Quest> quests = user.getQuests();
        System.out.println(quests);
        Quest quest = quests.iterator().next();
        //unidirectional OneToMany
        Collection<Question> questions = quest.getQuestions();
        System.out.println(questions);
        Question question = questions.iterator().next();
        //unidirectional OneToMany
        Collection<Answer> answers = question.getAnswers();
        System.out.println(answers);
        System.out.println(user.getGames());
        //ManyToMany
        System.out.println(user.getQuestsInGame());
    }

    @Test
    void print(){
        User user = session.find(User.class, PRIMARY_KEY);
        System.out.println(user);
        List<Quest> quests = user.getQuests();
        for (Quest quest : quests) {
            System.out.println(quest);
            Collection<Question> questions = quest.getQuestions();
            for (Question question : questions) {
                System.out.println(question);
            }
        }
    }

    @Test
    void tryCacheSecondLevel(){
        User user = session.find(User.class, PRIMARY_KEY);
        for (int i = 0; i < 10; i++) {
            User tmpUser = session.find(User.class, PRIMARY_KEY);
            System.out.println(tmpUser);
        }
    }
}