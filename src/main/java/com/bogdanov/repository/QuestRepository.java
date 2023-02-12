package com.bogdanov.repository;

import com.bogdanov.entity.Role;
import com.bogdanov.entity.Quest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class QuestRepository extends BaseRepository<Quest>{
    public QuestRepository() {
        create(new Quest(0L,0L,"Quest_1","Description","","",1L, Arrays.asList(1L,2L,3L)));
    }

    @Override
    public Stream<Quest> find(Quest pattern) {
        return map.values()
                .stream()
                .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                .filter(u -> nullOrEquals(pattern.getName(), u.getName()))
                .filter(u -> nullOrEquals(pattern.getText(), u.getText()))
                .filter(u -> nullOrEquals(pattern.getAuthorId(), u.getAuthorId()))
                .filter(u -> nullOrEquals(pattern.getStartQuestionId(), u.getStartQuestionId()));
    }



}
