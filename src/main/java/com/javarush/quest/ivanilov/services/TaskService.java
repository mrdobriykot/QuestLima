package com.javarush.quest.ivanilov.services;


import com.javarush.quest.ivanilov.entities.game.Task;
import com.javarush.quest.ivanilov.repositories.Repository;
import com.javarush.quest.ivanilov.repositories.TaskRepository;

public enum TaskService {

    TASK_SERVICE;

    private final Repository<Task> repo = new TaskRepository();

    public Task create(Task task) {
        return repo.create(task);
    }

    public Task get(long id) {
        return repo.get(id);
    }

    public Task update(Task task) {
        return repo.update(task);
    }
}
