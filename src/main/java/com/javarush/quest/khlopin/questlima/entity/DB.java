package com.javarush.quest.khlopin.questlima.entity;

import com.javarush.quest.khlopin.questlima.services.UserRepository;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DB {

    public static UserRepository userDataBase = new UserRepository();

}
