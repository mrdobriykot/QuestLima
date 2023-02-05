package com.javarush.quest.khlopin.questlima.entity.game;

import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer extends GameEntity {

    private Long answerId;
    private String text;
    private Long questionId;

    private AnswerState answerState;
    private String finishAnswerText;

    public Answer(Long answerId, String text, Long questionId, AnswerState answerState) {
        this.answerId = answerId;
        this.text = text;
        this.questionId = questionId;
        this.answerState = answerState;
    }
}
