package com.javarush.quest.uzienko.questlima.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Answer extends Entity {
    private Long questionId;
    private String text;
    private Long nextQuestionId;
}
