package com.javarush.khmelov.entity;

import com.javarush.khmelov.repository.hibernate.SessionCreator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestTest extends BaseTest {



    @Test
    void getId() {
        Quest quest = session.get(Quest.class, ID);
        User author = quest.getAuthor();
        System.out.println(quest+" "+author);
    }


}