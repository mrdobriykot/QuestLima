package com.javarush.quest.ivanilov.utils.transfers;

import com.javarush.quest.ivanilov.entities.users.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
    private long id;
    private String login;
    private String password;
    private Role role;
    private String currentGameName;
    private long gamesPlayed;
    private long gamesWon;
}
