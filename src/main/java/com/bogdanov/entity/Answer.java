package com.bogdanov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer implements AbstaractEntity {
    private Long id;
    private Long idAnswer;
    private Long questionId;
    private String text;
    private Long nextQuestionId;
    private Boolean status;



}
