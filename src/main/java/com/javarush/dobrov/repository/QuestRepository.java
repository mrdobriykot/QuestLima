package com.javarush.dobrov.repository;

import com.javarush.dobrov.entity.Quest;
import com.javarush.dobrov.entity.Question;

import java.util.Collection;
import java.util.stream.Stream;

public class QuestRepository extends BaseRepository<Quest> {


    @Override
    public Stream<Quest> find(Quest pattern) {
        return map.values()
                .stream()
                .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                .filter(u -> nullOrEquals(pattern.getText(), u.getText()))
                .filter(u -> nullOrEquals(pattern.getAllQuestions(), u.getAllQuestions()));

    }
}
