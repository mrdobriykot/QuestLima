package com.bogdanov.entity;
import com.bogdanov.entity.*;

import java.util.ArrayList;

import java.util.Collection;

public class Quest implements AbstaractEntity {
    private Long id;
    private User user;
    private String name;
    private String text;
    private Long startQuestionId;
    private final Collection<User> players = new ArrayList<>();
    private final Collection<Question> qustions = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
