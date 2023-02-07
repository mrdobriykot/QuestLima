package com.javarush.quest.khlopin.questlima.excpetions;

public class QuestException extends RuntimeException {
    public QuestException() {
        super();
    }

    public QuestException(String message) {
        super(message);
    }
}
