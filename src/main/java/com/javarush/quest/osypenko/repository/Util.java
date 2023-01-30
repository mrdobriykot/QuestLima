package com.javarush.quest.osypenko.repository;

import com.javarush.quest.osypenko.repository.entityDB.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Util {
    private static final TreeMap<Long, DB> CORE_1 = new Core1().getMap();
    private static final TreeMap<Long, DB> CORE_2 = new Core2().getMap();
    private static final TreeMap<Long, DB> MULTITHREADING = new Multithreading().getMap();
    private static final TreeMap<Long, DB> ALGORITHMS = new Algorithms().getMap();
    private static final TreeMap<Long, DB> PATTERNS = new Patterns().getMap();
    private static final TreeMap<Long, DB> ALL_QUESTION = new TreeMap<>();
    private static final HashMap<Entity, Training> ALL_ENTITY_DB = new HashMap<>();
    private static final List<Object> QUEST_INTERVIEW = new ArrayList<>();

    public TreeMap<Long, DB> getAllQuestion() {
        ALL_QUESTION.putAll(CORE_1);
        ALL_QUESTION.putAll(CORE_2);
        ALL_QUESTION.putAll(MULTITHREADING);
        ALL_QUESTION.putAll(ALGORITHMS);
        ALL_QUESTION.putAll(PATTERNS);
        return ALL_QUESTION;
    }

    public HashMap<Entity, Training> getAllEntityDB() {
        ALL_ENTITY_DB.put(Entity.CORE1, new Core1());
        ALL_ENTITY_DB.put(Entity.CORE2, new Core2());
        ALL_ENTITY_DB.put(Entity.MULTITHREADING, new Multithreading());
        ALL_ENTITY_DB.put(Entity.ALGORITHMS, new Algorithms());
        ALL_ENTITY_DB.put(Entity.PATTERNS, new Patterns());
        return ALL_ENTITY_DB;
    }

    public List<Object> getQuestInterview() {
        TreeSet<Integer> random = new TreeSet<>();
        while (random.size() < 15) {
            random.add(ThreadLocalRandom.current().nextInt(0, getAllQuestion().size()));
        }

        List<Long> idQuest = new ArrayList<>(getAllQuestion().keySet());
        for (Integer integers : random) {
            Object aLong = idQuest.get(integers);
            QUEST_INTERVIEW.add(getAllQuestion().get(aLong));
        }
        return QUEST_INTERVIEW;
    }
}
