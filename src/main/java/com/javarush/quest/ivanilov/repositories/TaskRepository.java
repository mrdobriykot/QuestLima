package com.javarush.quest.ivanilov.repositories;

import com.javarush.quest.ivanilov.entities.game.Task;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Optional;

public class TaskRepository extends BaseRepository<Task> {

    @Override
    public Optional<Task> find(String pattern) {
        throw new NotImplementedException();
    }
}
