package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.entity.game.Quest;
import com.javarush.quest.khlopin.questlima.entity.game.Question;
import com.javarush.quest.khlopin.questlima.entity.user.Role;
import com.javarush.quest.khlopin.questlima.entity.user.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuestStarterTest {

    private final QuestStarter questStarter = new QuestStarter();
    private final HttpServletRequest request = Mockito.spy(HttpServletRequest.class);
    private final HttpServletRequest session = Mockito.spy(HttpServletRequest.class);

//    @Test
//    void  startQuest() {
//        HttpServletRequest mock = Mockito.spy(HttpServletRequest.class);
//        HttpSession session = Mockito.spy();
//        Mockito.when(session.getAttribute("user")).thenReturn(new User(999L,"testName","testPass", Role.USER,new ArrayList<>()));
//        Mockito.when(mock.getParameter("id")).thenReturn("1");
//        questStarter.startQuest(mock);
//        Quest quest = questStarter.getQuest();
//        assertNotNull(quest);
//    }

    @Test
    void nextStageOfQuest() {
//        questStarter.nextStageOfQuest(request,"true",2);
//        Question question = questStarter.getQuestion();
//        assertNotNull(question);
    }

    @Test
    void checkWin() {
    }

    @Test
    void findTrueAnswer() {
    }
}