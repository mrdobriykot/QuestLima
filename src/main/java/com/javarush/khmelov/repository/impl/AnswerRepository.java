package com.javarush.khmelov.repository.impl;

import com.javarush.khmelov.entity.Answer;
import com.javarush.khmelov.repository.BaseRepository;
import com.javarush.khmelov.repository.helper.SessionCreator;

public class AnswerRepository extends BaseRepository<Answer> {

    public AnswerRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Answer.class);
    }
}
