package com.javarush.plugin.util;

import com.javarush.plugin.entity.Answer;
import com.javarush.plugin.entity.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public Long nextQuestionId = 0L;

     public Long findNextQuestionByAnswerId(String userAnswer, Collection<Answer> answers) {

        Long answerId = Long.valueOf(userAnswer);
        String findNextQuestionId = answers.stream()
                .filter(answer -> answer.getId() == answerId)
                .map(answer -> answer.getNextQuestionId())
                .collect(Collectors.toList()).toString();

        String test = findNextQuestionId.replaceAll("[\\[\\](){}]", "");
        Long questionId = Long.parseLong(test);

        nextQuestionId = questionId;
        return questionId;
    }
}
