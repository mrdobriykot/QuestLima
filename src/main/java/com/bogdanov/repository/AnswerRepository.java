package com.bogdanov.repository;

import com.bogdanov.entity.Role;
import com.bogdanov.entity.Answer;

import java.util.stream.Stream;

public class AnswerRepository extends BaseRepository<Answer>{


    public AnswerRepository() {
        create(new Answer(0L,1L,1L,"Answer",2L ,true));
        create(new Answer(0L,2L,1L,"Answer",2L ,false));
        create(new Answer(0L,3L,2L,"Answer",3L ,true));
        create(new Answer(0L,4L,2L,"Answer",3L ,false));
        create(new Answer(0L,5L,3L,"Answer",2L ,true));
        create(new Answer(0L,6L,3L,"Answer",2L ,false));

    }

    @Override
    public Stream<Answer> find(Answer pattern) {
        return map.values()
                .stream()
                .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                .filter(u -> nullOrEquals(pattern.getQuestionId(), u.getQuestionId()))
                .filter(u -> nullOrEquals(pattern.getText(), u.getText()))
                .filter(u -> nullOrEquals(pattern.getNextQuestionId(), u.getNextQuestionId()));

}



}
