package com.javarush.khmelov.service;

import com.javarush.khmelov.dto.QuestionTo;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.mapping.Dto;
import com.javarush.khmelov.repository.impl.QuestionRepository;
import javax.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

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
