package com.javarush.quest.osypenko.repository;

import java.io.Serializable;

public class DB implements Serializable {
    private final Long id;
    private final String question;
    private final String answer;
    private Object picture;

    public DB(Long id, String question, String text) {
        this.id = id;
        this.question = question;
        this.answer = text;
    }

    public DB(Long id, String question, String text, Object picture) {
        this.id = id;
        this.question = question;
        this.answer = text;
        this.picture = picture;
    }

    @Override
    public String toString() {
        return id + " " + question +
                " " + answer;
    }
}
