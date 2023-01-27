package com.javarush.alimin.service;

import com.javarush.alimin.entity.Question;
import com.javarush.alimin.repository.AnswerRepository;
import com.javarush.alimin.repository.QuestionRepository;

import java.util.Collection;

public enum QuestionService {

    QUESTION_SERVICE;

    private final QuestionRepository repository = new QuestionRepository(new AnswerRepository());

    public Collection<Question> getAll(){
        return repository.getAll();
    }

    public Question get(long id) {
        return repository.get(id);
    }

    public void create(Question question) {
        repository.create(question);
    }


}
