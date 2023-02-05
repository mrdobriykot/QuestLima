package com.javarush.quest.khlopin.questlima.services;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QuestStarterTest {

//    private final QuestStarter questStarter = new QuestStarter();
//    private final HttpServletRequest request = Mockito.spy(HttpServletRequest.class);
//    private final HttpServletRequest session = Mockito.spy(HttpServletRequest.class);


    //Александр, пробовал сделать тесты, но не вышло, не знаю как мокнуть сессию. Метод кладёт в сессию атрибут, не знаю как проверить положился ли он...
    // Не уверен, что через verify правильно т.к я просто проверю вызываться ли этот метод, но положился ли атрибут в сессию не узнаю.
    // Подскажите, пожалуйста, как сделать правильно






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