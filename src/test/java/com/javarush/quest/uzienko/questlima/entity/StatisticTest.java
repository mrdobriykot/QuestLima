package com.javarush.quest.uzienko.questlima.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatisticTest {

    @Test
    void incrementGameCount() {
        Statistic statistic = new Statistic();
        statistic.incrementGameCount();
        statistic.incrementGameCount();
        statistic.incrementGameCount();
        assertEquals(3, statistic.getGameCount());
    }

    @Test
    void incrementQuestionCount() {
        Statistic statistic = new Statistic();
        statistic.incrementQuestionCount();
        statistic.incrementQuestionCount();
        statistic.incrementQuestionCount();
        assertEquals(3, statistic.getQuestionCount());
    }
}