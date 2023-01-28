package com.javarush.plugin.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements AbstractEntity {

    private Long id;
    private String login;
    private String password;
    private Role role;

    private final Collection<Quest> quests = new ArrayList<>();
    private final Collection<Game> games = new ArrayList<>();

}
