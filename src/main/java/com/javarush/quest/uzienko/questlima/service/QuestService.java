package com.javarush.quest.uzienko.questlima.service;

import com.javarush.quest.uzienko.questlima.config.Winter;
import com.javarush.quest.uzienko.questlima.entity.Quest;
import com.javarush.quest.uzienko.questlima.repository.QuestMapRepository;
import com.javarush.quest.uzienko.questlima.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public class QuestService implements CrudService<Quest> {
    private final Repository<Quest> repository = Winter.getBean(QuestMapRepository.class);

    @Override
    public void create(Quest entity) {
        repository.create(entity);
    }

    @Override
    public void update(Quest entity) {
        repository.update(entity);
    }

    @Override
    public void delete(Quest entity) {
        repository.delete(entity);
    }

    @Override
    public Collection<Quest> getAll() {
        return repository.getAll();
    }

    @Override
    public Optional<Quest> get(Long id) {
        return repository.get(id);
    }
}
