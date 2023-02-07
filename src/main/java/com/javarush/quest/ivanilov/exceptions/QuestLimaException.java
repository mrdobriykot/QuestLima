package com.javarush.quest.ivanilov.exceptions;

public class QuestLimaException extends RuntimeException {
    public QuestLimaException(String message) {
        super(message);
    }

    public QuestLimaException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestLimaException(Throwable cause) {
        super(cause);
    }
}
