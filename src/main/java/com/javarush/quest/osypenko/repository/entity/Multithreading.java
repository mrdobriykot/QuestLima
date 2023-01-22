package com.javarush.quest.osypenko.repository.entity;

import com.javarush.quest.osypenko.repository.DB;
import com.javarush.quest.osypenko.repository.Training;

import java.util.HashMap;

public class Multithreading implements Training {
    private final HashMap<Long, DB> map = new HashMap<>();

    public Multithreading() {
        map.put(3000L, new DB(1L, "Что такое ООП?", "ООП - методология программирования, основанная на представлении программы в виде совокупности объектов, каждый из которых является экземпляром определенного класса, а классы образуют иерархию наследования. " +
                "Согласно парадигмы ООП программа состоит из объектов, обменивающихся сообщениями. Объекты могут обладать состоянием, единственный способ изменить состояние объекта - передать ему сообщение,  в ответ на которое, объект может изменить собственное состояние. " +
                "Класс — это описание еще не созданного объекта, как бы общий шаблон, состоящий из полей, методов и конструктора, а объект – экземпляр класса, созданный на основе этого описания."));
    }

    @Override
    public HashMap<Long, DB> getMap() {
        return map;
    }

}
