package com.javarush.khmelov.entity;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserTest extends BaseTest{

    @Test
    void getId() {
        User user = session.get(User.class, ID);
        System.out.println(user);
        //OneToOne
        System.out.println(user.getUserInfo());
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
}