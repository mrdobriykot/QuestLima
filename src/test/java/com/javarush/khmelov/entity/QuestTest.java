package com.javarush.khmelov.entity;

import org.junit.jupiter.api.Test;

class QuestTest extends BaseTest {


    public static final long ID = 1L;

    @Test
    void getId() {
        Quest quest = session.get(Quest.class, ID);
        User author = quest.getAuthor();
        System.out.println(quest+" "+author);
    }


}