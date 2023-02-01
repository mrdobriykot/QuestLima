package com.javarush.quest.ivanilov.entities.users;

import com.javarush.quest.ivanilov.entities.AbstractEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class User extends AbstractEntity implements Serializable {

    private transient String login;
    private transient String password;
    private transient Roles role;
    private transient long currentGameId;
    private transient List<Long> gamesPlayed;
}
