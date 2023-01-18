package com.javarush.quest.khlopin.questdelta.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private long id;
    private String userName;
    private String password;
    private Role role;
}
