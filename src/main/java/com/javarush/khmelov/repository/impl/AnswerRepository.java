package com.javarush.khmelov.repository.impl;

import com.javarush.khmelov.config.SessionCreator;
import com.javarush.khmelov.entity.Answer;
import com.javarush.khmelov.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerRepository extends BaseRepository<Answer> {

    public AnswerRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Answer.class);
    }
}
