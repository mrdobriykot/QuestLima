package com.javarush.quest.torkel.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements AbstractEntity{

    private Long id;

    private String login;

    private String password;

    private Role role;

    private int gamesCount;

    private final Collection<Quest> quests = new ArrayList<>();

    private final Collection<Game> games = new ArrayList<>();

    public String getImage() { //TODO move to DTO
        return "user-" + id;
    }




}
