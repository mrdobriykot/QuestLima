package com.javarush.quest.marzhiievskyi.service;

import com.javarush.quest.marzhiievskyi.entity.Answer;
import com.javarush.quest.marzhiievskyi.repository.AnswerRepository;
import com.javarush.quest.marzhiievskyi.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public enum AnswerService {
    ANSWER_SERVICE;
    private final Repository<Answer> answerRepository = new AnswerRepository();

    public void create(Answer answer) {
        answerRepository.create(answer);
    }

    public void update(Answer answer) {
        answerRepository.update(answer);
    }

    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }

    public Answer get(Long answerId) {
        return answerRepository.get(answerId);
    }

    public Collection<Answer> getAll() {
        return answerRepository.getAll();
    }

    public Optional<Answer> getByQuestionId(Long questionId) {
        Answer answerToFind = Answer.builder()
                .questionId(questionId)
                .build();
        return answerRepository.find(answerToFind).findAny();
    }

}
