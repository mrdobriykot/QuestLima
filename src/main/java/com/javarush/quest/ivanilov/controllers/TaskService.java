package com.javarush.quest.ivanilov.controllers;


import com.javarush.quest.ivanilov.entities.game.Task;
import com.javarush.quest.ivanilov.repositories.Repository;
import com.javarush.quest.ivanilov.repositories.TaskRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Optional;
@Slf4j
public enum TaskService {

    TASK_SERVICE;

    private final Repository<Task> repo = new TaskRepository();

    public Collection<Task> getAll() {
        return repo.getAll();
    }

    public Task create(Task task) {
        return repo.create(task);
    }

    public Task get(long id) {
        return repo.get(id);
    }

    public Task update(Task task) {
        return repo.update(task);
    }

    public void delete(Task task) {
        repo.delete(task);
    }

    public Optional<Task> find(String pattern) {
        return repo.find(pattern);
    }
}
