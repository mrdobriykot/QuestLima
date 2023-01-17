package com.javarush.quest.marzhiievskyi.repository;

import com.javarush.quest.marzhiievskyi.entity.Question;
import com.javarush.quest.marzhiievskyi.entity.User;

import java.util.stream.Stream;

public class QuestionRepository extends BaseRepository<Question> {

    @Override
    public Stream<Question> find(Question pattern) {
        return map.values().stream()
                .filter(question -> nullOrEquals(pattern.getId(), question.getId()))
                .filter(question -> nullOrEquals(pattern.getText(), question.getText()))
                .filter(question -> nullOrEquals(pattern.getQuestId(), question.getQuestId()))
                .filter(question -> nullOrEquals(pattern.getGameState(), question.getGameState()));
    }
}
