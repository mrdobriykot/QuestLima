package com.javarush.quest.uzienko.questlima.service;

import com.javarush.quest.uzienko.questlima.entity.Statistic;
import com.javarush.quest.uzienko.questlima.entity.StatisticDto;

import javax.servlet.http.HttpSession;

public class StatisticService {

    private static final String STATISTIC = "statistic";

    public void updateQuest(HttpSession session) {
        Statistic statistic = getStatisticFromSession(session);
        statistic.incrementGameCount();
    }

    public void updateQuestion(HttpSession session) {
        Statistic statistic = getStatisticFromSession(session);
        statistic.incrementQuestionCount();
    }

    public StatisticDto getStatistic(HttpSession session) {
        Statistic statistic = getStatisticFromSession(session);
        return new StatisticDto(statistic.getGameCount(), statistic.getQuestionCount());
    }

    private Statistic getStatisticFromSession(HttpSession session) {
        Statistic result = (Statistic) session.getAttribute(STATISTIC);
        if (result == null) {
            result = new Statistic();
            session.setAttribute(STATISTIC, result);
        }
        return result;
    }
}
