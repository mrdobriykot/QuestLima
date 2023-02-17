package com.javarush.alimin.repository;

import com.javarush.alimin.entity.Question;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@SuppressWarnings("unused")
public class QuestionRepository extends BaseRepository<Question>{

    private final AnswerRepository repository;
}

