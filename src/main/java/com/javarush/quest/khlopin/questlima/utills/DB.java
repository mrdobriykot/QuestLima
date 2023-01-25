package com.javarush.quest.khlopin.questlima.utills;

import com.javarush.quest.khlopin.questlima.entity.game.Answer;
import com.javarush.quest.khlopin.questlima.services.AnswerRepository;
import com.javarush.quest.khlopin.questlima.services.QuestRepository;
import com.javarush.quest.khlopin.questlima.services.QuestionRepository;
import com.javarush.quest.khlopin.questlima.services.UserRepository;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DB {
    public static AnswerRepository answerDataBase = new AnswerRepository();
    public static QuestionRepository questionDataBase = new QuestionRepository();
    public static UserRepository userDataBase = new UserRepository();
    public static QuestRepository questDataBase = new QuestRepository();

}
