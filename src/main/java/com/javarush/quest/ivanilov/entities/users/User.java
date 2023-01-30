package com.javarush.quest.ivanilov.entities.users;

import com.javarush.quest.ivanilov.entities.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
public class User extends AbstractEntity {
    private String login;
    private String password;
    private Roles role;
    private long currentGameId;
    private List<Long> gamesPlayed;
}
