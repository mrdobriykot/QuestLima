package com.javarush.khmelov.mapping;

import com.javarush.khmelov.dto.QuestTo;
import com.javarush.khmelov.dto.UserTo;
import com.javarush.khmelov.entity.Quest;
import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DtoTest {

    private final Dto mapper = Dto.MAPPER;

    @Test
    void getUserDto() {
        User user = User.builder().id(1L).login("test").role(Role.ADMIN).build();
        UserTo userTo = mapper.from(user);
        System.out.println(userTo);
        assertEquals(user.getId(), userTo.getId());
    }


    @Test
    void getQuestDto() {
        User user = User.builder().id(1L).role(Role.ADMIN).build();
        Quest quest = Quest.builder()
                .id(2L)
                .author(user)
                .startQuestionId(3L)
                .name("Question name")
                .text("Question text")
                .build();
        QuestTo questTo = mapper.from(quest);
        System.out.println(questTo);
        assertEquals(quest.getId(), questTo.getId());
    }
}