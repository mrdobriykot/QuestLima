package com.javarush.quest.uzienko.questlima.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Quest extends Entity {
    private String name;
    private String description;
    private Long startQuestionId;
}
