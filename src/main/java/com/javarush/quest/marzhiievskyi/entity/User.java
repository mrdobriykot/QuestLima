package com.javarush.quest.marzhiievskyi.entity;
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
    private Collection<GameSession> games = new ArrayList<>();



}
