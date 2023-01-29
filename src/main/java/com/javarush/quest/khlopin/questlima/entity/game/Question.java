package com.javarush.quest.khlopin.questlima.entity.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Question extends GameEntity {

    private Long id;

    private Long questId;
    private Long nextQuestionId;

    private String text;
    private List<Answer> answerList;

}
