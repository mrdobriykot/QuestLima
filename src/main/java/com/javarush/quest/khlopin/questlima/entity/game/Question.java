package com.javarush.quest.khlopin.questlima.entity.game;

import lombok.*;


import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question extends GameEntity {

    private Long id;

    private Long questId;
    private Long nextQuestionId;

    private String text;
    private List<Answer> answerList;

}
