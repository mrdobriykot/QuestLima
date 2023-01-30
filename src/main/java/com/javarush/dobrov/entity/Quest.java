package com.javarush.dobrov.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Quest implements AbstractEntity{

    private String header;
    private Long questId;
    private String text;

    public Map<Long, Question> questions = new HashMap<>();

    public Quest(String header, String text) {
        this.header=header;
        this.text = text;
    }


    @Override
    public void setId(long id) {
        questId=id;
    }

    @Override
    public Long getId() {
        return questId;
    }

    public Map<Long, Question> getAllQuestions(){
        return questions;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
