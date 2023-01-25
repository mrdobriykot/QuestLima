package com.javarush.quest.khlopin.questlima.entity.game;

import com.javarush.quest.khlopin.questlima.entity.GameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class Question extends GameEntity {

    private Long id;
    private Long nextQuestionId;

    private String text;
    private List<Answer> answerList;

}
