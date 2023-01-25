package com.javarush.quest.khlopin.questlima.entity.game;

import com.javarush.quest.khlopin.questlima.entity.GameEntity;
import com.javarush.quest.khlopin.questlima.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Quest extends GameEntity {

    private Long id;
    private User author;
    private String info;
    private List<Question> questionList;

    private int countOfStages;





}
