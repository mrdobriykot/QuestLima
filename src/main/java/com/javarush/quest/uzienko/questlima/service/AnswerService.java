package com.javarush.quest.uzienko.questlima.service;

import com.javarush.quest.uzienko.questlima.config.Winter;
import com.javarush.quest.uzienko.questlima.entity.Answer;
import com.javarush.quest.uzienko.questlima.repository.AnswerMapRepository;
import com.javarush.quest.uzienko.questlima.repository.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public class AnswerService implements CrudService<Answer> {

    private final Repository<Answer> repository = Winter.getBean(AnswerMapRepository.class);

    @Override
    public void create(Answer entity) {
        repository.create(entity);
    }

    @Override
    public void update(Answer entity) {
        repository.update(entity);
    }

    @Override
    public void delete(Answer entity) {
        repository.delete(entity);
    }

    @Override
    public Collection<Answer> getAll() {
        return repository.getAll();
    }

    @Override
    public Optional<Answer> get(Long id) {
        return repository.get(id);
    }

    public Stream<Answer> getAllByQuestionId(Long id) {
        Answer pattern = new Answer();
        pattern.setQuestionId(id);
        return repository.find(pattern);
    }
}
