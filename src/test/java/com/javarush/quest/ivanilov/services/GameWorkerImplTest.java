package com.javarush.quest.ivanilov.services;

import com.javarush.quest.ivanilov.entities.game.*;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class GameWorkerImplTest {
    GameService gameService = Mockito.mock(GameService.class);
    UserService userService = Mockito.mock(UserService.class);
    QuestService questService = Mockito.mock(QuestService.class);
    GameWorker worker = new GameWorkerImpl(gameService, userService, questService);

    @Test
    void getOptionsToSend() {
        Map<Integer, String> options = new HashMap<>();
        options.put(1, "Option1");
        options.put(2, "Option2");

        Task question = Task.builder()
                .type(TaskType.QUESTION)
                .Options(options)
                .build();

        Event event  = Event.builder()
                .questId(1)
                .message("Generic message")
                .eventType(EventType.TASK)
                .task(question)
                .name("Generic message")
                .build();

        Option[] optionsToSend = worker.getOptionsToSend(event);
        Assertions.assertEquals(2, optionsToSend.length);
        Assertions.assertEquals("Option1", optionsToSend[0].getName());
        Assertions.assertEquals("Option2", optionsToSend[1].getName());
    }

    @Test
    void getFightToSend() {
        HttpSession session = Mockito.mock(HttpSession.class);
        Quest quest = Mockito.mock(Quest.class);
        Hero hero = Mockito.mock(Hero.class);
        Mockito.doReturn(quest).when(questService).get(1);
        Mockito.doReturn(hero).when(quest).getHero();

        Hero villain = Hero.builder().strength(5).health(30).name("Bob").build();
        Task fight = Task.builder().type(TaskType.FIGHT).description("Sample Description").villain(villain).build();
        fight.setId(134);
        Event eventFight  = Event.builder().questId(1).message("Generic message").eventType(EventType.TASK).task(fight).name("Generic message").build();
        eventFight.setId(1);
        Fight fightToSend = worker.getFightToSend(session, eventFight);
        Task question = Task.builder().type(TaskType.QUESTION).build();
        Event eventWrong = Event.builder().task(question).build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> worker.getFightToSend(session, eventWrong));
        Assertions.assertEquals(1, fightToSend.getEventId());
        Assertions.assertEquals(villain, fightToSend.getVillain());
        Assertions.assertEquals(hero, fightToSend.getHero());
        Assertions.assertEquals(134, fightToSend.getTaskId());
    }
}