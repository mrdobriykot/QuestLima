package com.javarush.quest.uzienko.questlima.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatisticDto {
    private Integer gameCount;
    private Integer questionCount;
}
