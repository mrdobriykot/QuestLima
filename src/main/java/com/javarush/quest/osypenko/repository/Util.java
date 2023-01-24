package com.javarush.quest.osypenko.repository;

import com.javarush.quest.osypenko.repository.entityDB.*;

import java.util.HashMap;
import java.util.TreeMap;

public class Util {
    private static final TreeMap<Long, DB> core1 = new Core1().getMap();
    private static final TreeMap<Long, DB> core2 = new Core2().getMap();
    private static final TreeMap<Long, DB> multithreading = new Multithreading().getMap();
    private static final TreeMap<Long, DB> algorithms = new Algorithms().getMap();
    private static final TreeMap<Long, DB> patterns = new Patterns().getMap();
    private static final TreeMap<Long, DB> mapEntity = new TreeMap<>();
    private static final HashMap<Entity, Training> objectHashMap = new HashMap<>();

    public TreeMap<Long, DB> getMapEntity() {
        mapEntity.putAll(core1);
        mapEntity.putAll(core2);
        mapEntity.putAll(multithreading);
        mapEntity.putAll(algorithms);
        mapEntity.putAll(patterns);
        return mapEntity;
    }

    public HashMap<Entity, Training> getObjectHashMap() {
        objectHashMap.put(Entity.CORE1, new Core1());
        objectHashMap.put(Entity.CORE2, new Core2());
        objectHashMap.put(Entity.MULTITHREADING, new Multithreading());
        objectHashMap.put(Entity.ALGORITHMS, new Algorithms());
        objectHashMap.put(Entity.PATTERNS, new Patterns());
        return objectHashMap;
    }
}
