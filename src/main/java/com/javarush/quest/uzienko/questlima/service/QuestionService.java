package com.javarush.quest.uzienko.questlima.service;

import com.javarush.quest.uzienko.questlima.config.Winter;
import com.javarush.quest.uzienko.questlima.entity.Question;
import com.javarush.quest.uzienko.questlima.repository.QuestionMapRepository;
import com.javarush.quest.uzienko.questlima.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public class QuestionService implements CrudService<Question> {

    private final Repository<Question> repository = Winter.getBean(QuestionMapRepository.class);

    @Override
    public void create(Question entity) {
        repository.create(entity);
    }

    @Override
    public void update(Question entity) {
        repository.update(entity);
    }

    @Override
    public void delete(Question entity) {
        repository.delete(entity);
    }

    @Override
    public Collection<Question> getAll() {
        return repository.getAll();
    }

    @Override
    public Optional<Question> get(Long id) {
        return repository.get(id);
    }
}
