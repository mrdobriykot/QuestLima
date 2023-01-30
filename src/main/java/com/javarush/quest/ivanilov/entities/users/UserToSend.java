package com.javarush.quest.ivanilov.entities.users;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserToSend {
    private long id;
    private String login;
    private String password;
    private Roles role;
    private String currentGameName;
    private long gamesPlayed;
    private long gamesWon;
}
