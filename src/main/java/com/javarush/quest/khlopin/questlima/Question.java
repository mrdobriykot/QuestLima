package com.javarush.quest.khlopin.questlima;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class Question {

    private Long id;
    private int nextQuestionId;
    private List<Answer> answerList;

}
