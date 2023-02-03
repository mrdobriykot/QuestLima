package com.bogdanov.repository;


import com.bogdanov.entity.Quest;

import java.util.stream.Stream;

public class QuestRepository extends BaseRepository<Quest>{

    public QuestRepository() {
        create(new Quest(1L,1L,"Космос","Описание","1.1.Ответ1\n1.2.Ответ2\n2.1.Ответ1\n2.2.Ответ2\n","1.Вопрос\n2.Вопрос",0L));

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
