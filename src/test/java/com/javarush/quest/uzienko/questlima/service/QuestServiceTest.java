package com.javarush.quest.uzienko.questlima.service;

import com.javarush.quest.uzienko.questlima.config.Winter;
import com.javarush.quest.uzienko.questlima.entity.Quest;
import com.javarush.quest.uzienko.questlima.repository.QuestMapRepository;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("ALL")
class QuestServiceTest {

    @Test
    void createTest() {
        try (MockedStatic<Winter> winter = Mockito.mockStatic(Winter.class)) {
            //Assert
            winter.when(() -> Winter.getBean(QuestMapRepository.class)).thenReturn(new QuestMapRepository());
            QuestService questService = new QuestService();
            Collection<Quest> questCollection = questService.getAll();
            assertTrue(questCollection.isEmpty());

            //Act
            questService.create(new Quest("nameTest", "descriptionTest", 0L));

            //Assert
            assertEquals(questCollection.size(), 1);
        }
    }

    @Test
    void updateTest() {
        try (MockedStatic<Winter> winter = Mockito.mockStatic(Winter.class)) {
            //Arrange
            winter.when(() -> Winter.getBean(QuestMapRepository.class)).thenReturn(new QuestMapRepository());
            QuestService questService = new QuestService();
            Collection<Quest> questCollection = questService.getAll();
            assertTrue(questCollection.isEmpty());
            Quest quest = new Quest("nameTest", "descriptionTest", 0L);
            questService.create(quest);
            assertEquals("nameTest", questService.get(quest.getId()).get().getName());

            Quest questNew = new Quest();
            questNew.setId(quest.getId());
            questNew.setName("newName");

            //Act
            questService.update(questNew);

            //Assert
            assertEquals("newName", questService.get(quest.getId()).get().getName());
        }
    }

    @Test
    void deleteTest() {
        try (MockedStatic<Winter> winter = Mockito.mockStatic(Winter.class)) {
            //Arrange
            winter.when(() -> Winter.getBean(QuestMapRepository.class)).thenReturn(new QuestMapRepository());
            QuestService questService = new QuestService();
            Collection<Quest> questCollection = questService.getAll();
            Quest quest = new Quest("nameTest", "descriptionTest", 0L);
            questService.create(quest);
            assertEquals(questCollection.size(), 1);

            //Act
            questService.delete(quest);

            //Assert
            assertEquals(questCollection.size(), 0);
        }
    }

    @Test
    void getAllTest() {
        try (MockedStatic<Winter> winter = Mockito.mockStatic(Winter.class)) {
            //Arrange
            winter.when(() -> Winter.getBean(QuestMapRepository.class)).thenReturn(new QuestMapRepository());
            QuestService questService = new QuestService();
            Collection<Quest> questCollection = questService.getAll();
            assertTrue(questCollection.isEmpty());

            Quest quest0 = new Quest("newQuest0", "newQuest0Desc", 0L);
            Quest quest1 = new Quest("newQuest1", "newQuest1Desc", 1L);
            Quest quest2 = new Quest("newQuest2", "newQuest2Desc", 2L);
            questService.create(quest0);
            questService.create(quest1);
            questService.create(quest2);

            //Act
            Collection<Quest> all = questService.getAll();

            //Assert
            assertEquals(3, all.size());
            assertTrue(all.stream()
                    .anyMatch(quest0::equals));
            assertTrue(all.stream()
                    .anyMatch(quest1::equals));
            assertTrue(all.stream()
                    .anyMatch(quest2::equals));
        }
    }

    @Test
    void getTest() {
        try (MockedStatic<Winter> winter = Mockito.mockStatic(Winter.class)) {
            //Arrange
            winter.when(() -> Winter.getBean(QuestMapRepository.class)).thenReturn(new QuestMapRepository());
            QuestService questService = new QuestService();
            Collection<Quest> questCollection = questService.getAll();
            assertTrue(questCollection.isEmpty());

            Quest quest0 = new Quest("newQuest0", "newQuest0Desc", 0L);
            questService.create(quest0);

            //Act
            Quest result = questService.get(quest0.getId()).get();

            //Assert
            assertEquals(quest0, result);
        }
    }
}