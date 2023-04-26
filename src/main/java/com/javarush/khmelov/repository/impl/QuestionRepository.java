package com.javarush.khmelov.repository.impl;

import com.javarush.khmelov.config.SessionCreator;
import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.repository.BaseRepository;

public class QuestionRepository extends BaseRepository<Question> {

    public QuestionRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Question.class);
    }
}
