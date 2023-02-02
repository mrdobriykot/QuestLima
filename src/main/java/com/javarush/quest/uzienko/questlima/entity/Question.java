package com.javarush.quest.uzienko.questlima.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Question extends Entity {
    private Long questId;
    private String text;
    private QuestState questState;
}
