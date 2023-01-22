package com.javarush.quest.khlopin.questlima;

import com.javarush.quest.khlopin.questlima.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
public class Quest {

    public Long id;
    public User author;

    public String info;

    public List<Question> list = new ArrayList<>();





}
