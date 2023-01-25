package com.javarush.quest.osypenko.repository;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

public class DB implements Serializable {
    private final Long id;
    private final String question;
    private final String answer;
    private HashMap<String, String> url;
    private String[] images;

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

    public DB(Long id, String question, String text, String[] images) {
        this.id = id;
        this.question = question;
        this.answer = text;
        this.images = images;
    }

    public DB(Long id, String question, String text, HashMap<String, String> url, String[] images) {
        this.id = id;
        this.question = question;
        this.answer = text;
        this.url = url;
        this.images = images;
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

    public String[] getImages() {
        return images;
    }

    @Override
    public String toString() {
        return "DB{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", url=" + url +
                ", images=" + Arrays.toString(images) +
                '}';
    }
}
