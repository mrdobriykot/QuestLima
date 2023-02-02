package com.javarush.quest.uzienko.questlima.service;

import com.javarush.quest.uzienko.questlima.entity.Statistic;
import com.javarush.quest.uzienko.questlima.entity.StatisticDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StatisticServiceTest {

    private static final String STATISTIC = "statistic";

    @Mock
    HttpSession session;

    Statistic statistic;

    StatisticService statisticService = new StatisticService();

    @BeforeEach
    void init() {
        statistic = new Statistic();
        Mockito.doReturn(statistic).when(session).getAttribute(STATISTIC);
    }

    @Test
    void updateQuestTest() {
        statisticService.updateQuest(session);
        Mockito.verify(session, Mockito.times(1)).getAttribute(STATISTIC);
        assertEquals(1, statistic.getGameCount());
    }

    @Test
    void updateQuestionTest() {
        statisticService.updateQuestion(session);
        Mockito.verify(session, Mockito.times(1)).getAttribute(STATISTIC);
        assertEquals(1, statistic.getQuestionCount());
    }

    @Test
    void getStatisticTest() {
        StatisticDto statisticDto = statisticService.getStatistic(session);
        Mockito.verify(session, Mockito.times(1)).getAttribute(STATISTIC);
        assertEquals(0, statisticDto.getGameCount());
        assertEquals(0, statisticDto.getQuestionCount());
    }
}