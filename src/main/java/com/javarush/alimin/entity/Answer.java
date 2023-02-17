package com.javarush.alimin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer implements AbstractEntity{

    private Long id;

    private Long questionId;

    private String answerText;

    private Long nextQuestionId;

}
