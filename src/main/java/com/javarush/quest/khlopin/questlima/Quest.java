package com.javarush.quest.khlopin.questlima;

import com.javarush.quest.khlopin.questlima.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
public class Quest {

    private Long id;
    private User author;

    private String info;

    private List<Question> list;
    private User user;





}
