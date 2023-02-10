package com.bogdanov.repository;

import com.bogdanov.entity.GameState;
import com.bogdanov.entity.Role;
import com.bogdanov.entity.Question;

import java.util.stream.Stream;

public class QuestionRepository extends BaseRepository<Question>{


    public QuestionRepository() {
        create(new Question(0L,1L,1L,"Question", GameState.PLAY));
        create(new Question(0L,2L,1L,"Question", GameState.PLAY));
        create(new Question(0L,3L,1L,"Question", GameState.PLAY));
    }

    @Override
    public Stream<Question> find(Question pattern) {
        return map.values()
                .stream()
                .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                .filter(u -> nullOrEquals(pattern.getQuestId(), u.getQuestId()))
                .filter(u -> nullOrEquals(pattern.getGameState(), u.getGameState()))
                .filter(u -> nullOrEquals(pattern.getText(), u.getText()));
    }



}
