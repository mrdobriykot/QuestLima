package com.javarush.khmelov.repository.impl;

import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.repository.BaseRepository;
import com.javarush.khmelov.repository.helper.SessionCreator;

public class QuestionRepository extends BaseRepository<Question> {

    public QuestionRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Question.class);
    }
}
