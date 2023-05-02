package com.javarush.plugin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer implements AbstractEntity {

    private Long id;

    private String text;

    private Long questionId;

    private Long nextQuestionId;
}

