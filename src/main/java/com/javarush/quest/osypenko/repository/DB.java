package com.javarush.quest.osypenko.repository;

import java.io.Serializable;
import java.util.HashMap;

public class DB implements Serializable {
    private final Long id;
    private final String question;
    private final String answer;
    private HashMap<String, String> url;

    // TODO add patterns builder

    public DB(Long id, String question, String text) {
        this.id = id;
        this.question = question;
        this.answer = text;
    }

    public DB(Long id, String question, String text, HashMap<String, String> url) {
        this.id = id;
        this.question = question;
        this.answer = text;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public HashMap<String, String> getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "DB{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", url=" + url +
                '}';
    }
}
