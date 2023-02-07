package com.javarush.quest.uzienko.questlima.entity;

import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
public class Statistic {
    private final AtomicInteger gameCount = new AtomicInteger(0);
    private final AtomicInteger questionCount = new AtomicInteger(0);

    public int getGameCount() {
        return gameCount.get();
    }

    public int getQuestionCount() {
        return questionCount.get();
    }

    public void incrementGameCount() {
        while(true) {
            int existingValue = getGameCount();
            int newValue = existingValue + 1;
            if(gameCount.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    public void incrementQuestionCount() {
        while(true) {
            int existingValue = getQuestionCount();
            int newValue = existingValue + 1;
            if(questionCount.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }
}
