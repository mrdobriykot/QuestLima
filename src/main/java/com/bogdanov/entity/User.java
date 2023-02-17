package com.bogdanov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements AbstaractEntity{
    private Long id;
    private  String login;
    private String password;
     private Role role;
    private final Collection<User> players = new ArrayList<>();
    private final Collection<Question> qustions = new ArrayList<>();


    public String getImage(){
        return "user-"+id;
    }
}
