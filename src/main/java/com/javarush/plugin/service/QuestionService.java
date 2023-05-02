package com.javarush.plugin.service;

import com.javarush.plugin.entity.Answer;
import com.javarush.plugin.entity.GameState;
import com.javarush.plugin.entity.Question;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionService {

    public Long nextQuestionId = 0L;

    public Question getQuestion(HttpServletRequest request, Collection<Question> questions, Collection<Answer> answers ) {

        Long questionId;

        if (nextQuestionId == 0L) {
            questionId = 1L;
        } else{
            questionId = Long.parseLong(request.getParameter("id"));
        }

        System.out.println("DO GET: GET QUESTION: Q.ID: " + questionId);

        String questionText = questions.stream()
                .filter(q -> q.getId().equals(questionId))
                .map(question -> question.getText())
                .collect(Collectors.toList())
                .toString();

        String gameState = questions.stream()
                .filter(q -> q.getId().equals(questionId))
                .map(Question::getGameState).toList()
                .toString();

        String questionGameState = gameState.replaceAll("[\\[\\](){}]", "");

        Question question = Question
                .builder()
                .id(questionId)
                .text(questionText)
                .gameState(GameState.valueOf(questionGameState))
                .build();

        List<Answer> answerList = answers.stream()
                .filter(answer -> answer.getQuestionId() == questionId)
                .collect(Collectors.toList());

        for (Answer answer : answerList) {
            question.getAnswers().add(answer);
        }

        return question;
    }
}
