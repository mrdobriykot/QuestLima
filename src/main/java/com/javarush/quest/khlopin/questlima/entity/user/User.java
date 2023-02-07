package com.javarush.quest.khlopin.questlima.entity.user;

import com.javarush.quest.khlopin.questlima.entity.game.Game;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String userName;
    private String password;
    private Role role;

    private List<Game> gameList;

    public String toString() {
        return "User id=" + this.getId() + ", userName=" + this.getUserName() + ", role=" + this.getRole();
    }
}
