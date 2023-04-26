package com.javarush.khmelov.service;

import com.javarush.khmelov.dto.QuestionTo;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.mapping.Dto;
import com.javarush.khmelov.repository.impl.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public Optional<QuestionTo> get(long id) {
        return Optional.of(questionRepository.get(id))
                .map(Dto.MAPPER::from);
    }

    @SneakyThrows
    @Transactional
    public Optional<QuestionTo> update(Long questionId, String text) {
        Question question = questionRepository.get(questionId);
        question.setText(text);
        questionRepository.update(question);
        return Optional.of(question)
                .map(Dto.MAPPER::from);
    }
}
