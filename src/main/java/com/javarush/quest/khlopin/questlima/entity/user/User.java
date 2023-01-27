package com.javarush.quest.khlopin.questlima.entity.user;

import com.javarush.quest.khlopin.questlima.entity.game.Game;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String userName;
    private String password;
    private Role role;

    private List<Game> gameList;
}
