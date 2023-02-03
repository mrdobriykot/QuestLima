package com.javarush.quest.torkel.service;

import com.javarush.quest.torkel.entity.Question;
import com.javarush.quest.torkel.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.Optional;

@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Optional<Question> get(long id) {
        return Optional.of(questionRepository.get(id));
    }

    @SneakyThrows
    public Optional<Question> update(Long questionId, String text) {
        Question question = questionRepository.get(questionId);
        question.setText(text);
        questionRepository.update(question);
        return Optional.of(question);
    }
}
