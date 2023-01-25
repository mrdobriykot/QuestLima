package com.javarush.quest.khlopin.questlima;

import com.javarush.quest.khlopin.questlima.entity.game.Quest;
import com.javarush.quest.khlopin.questlima.entity.user.User;
import com.javarush.quest.khlopin.questlima.services.QuestionRepository;
import com.javarush.quest.khlopin.questlima.utills.DB;

public class TestsClass {

    public static void main(String[] args) {
        System.out.println(DB.userDataBase.getAll());



        System.out.println(DB.answerDataBase.getAll());

        System.out.println(DB.questionDataBase.getAll());



        System.out.println(DB.questDataBase.getAll());

        for (Quest quest : DB.questDataBase.getAll()) {
            String info = quest.getInfo();
            quest.getId();
        }

    }
}
