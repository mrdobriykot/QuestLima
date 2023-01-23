package com.javarush.quest.osypenko.repository.entity;

import com.javarush.quest.osypenko.repository.DB;
import com.javarush.quest.osypenko.repository.Training;

import java.util.HashMap;

public class Core2 implements Training {
    private final HashMap<Long, DB> map = new HashMap<>();
    private static final Long CONSTANT_ID = 2000L;

    public Core2() {
        map.put(2000L, new DB(2000L, "Что такое дженерики?", "\"Дженерики – это параметризованные типы.\\" +
                "С их помощью можно объявлять классы, интерфейсы и методы, в которых тип данных указан в виде параметра." +
                "Используя дженерики, можно создать единственный класс, который будет автоматически работать с разными типами данных. " +
                "Эта информация доступна только на этапе компиляции и стирается в runtime, и в байт код попадет только информация о том, что в программе есть некий список List<Object> list вместо List<String> list, например. " +
                "Появились в версии 1.5 \""));
    }

    @Override
    public Long getConstantID() {
        return CONSTANT_ID;
    }

    @Override
    public HashMap<Long, DB> getMap() {
        return map;
    }

}
