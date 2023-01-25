package com.javarush.quest.khlopin.questlima.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String userName;
    private String password;
    private Role role;

}
