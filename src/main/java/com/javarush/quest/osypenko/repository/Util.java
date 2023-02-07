package com.javarush.quest.osypenko.repository;

import com.javarush.quest.osypenko.repository.entityDB.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.quest.osypenko.costants.Constant.*;

public class Util {
    public static final Logger log = LogManager.getLogger(Util.class);
    private static final TreeMap<Long, DB> CORE_1 = Core1.getInstance().getMap();
    private static final TreeMap<Long, DB> CORE_2 = Core2.getInstance().getMap();
    private static final TreeMap<Long, DB> MULTITHREADING = Multithreading.getInstance().getMap();
    private static final TreeMap<Long, DB> ALGORITHMS = Algorithms.getInstance().getMap();
    private static final TreeMap<Long, DB> PATTERNS = Patterns.getInstance().getMap();
    private static final TreeMap<Long, DB> ALL_QUESTION = new TreeMap<>();
    private static final HashMap<Entity, Training> ALL_ENTITY_DB = new HashMap<>();
    private static final List<Object> QUEST_INTERVIEW = new ArrayList<>();

    public void getAllQuestion() {
        ALL_QUESTION.putAll(CORE_1);
        ALL_QUESTION.putAll(CORE_2);
        ALL_QUESTION.putAll(MULTITHREADING);
        ALL_QUESTION.putAll(ALGORITHMS);
        ALL_QUESTION.putAll(PATTERNS);
        log.debug(THE_MAP_WITH_THE_CONTENTS_OF_ALL_THE_MAPS_WAS_FILLED_WITH);
    }

    public HashMap<Entity, Training> getAllEntityDB() {
        if (ALL_ENTITY_DB.isEmpty()) {
            ALL_ENTITY_DB.put(Entity.CORE1, Core1.getInstance());
            ALL_ENTITY_DB.put(Entity.CORE2, Core2.getInstance());
            ALL_ENTITY_DB.put(Entity.MULTITHREADING, Multithreading.getInstance());
            ALL_ENTITY_DB.put(Entity.ALGORITHMS, Algorithms.getInstance());
            ALL_ENTITY_DB.put(Entity.PATTERNS, Patterns.getInstance());
            log.debug(THE_MAP_WITH_THE_CONTENTS_OF_ALL_DB_ENTITIES_IS_FILLED);
        }
        log.debug(A_MAP_WITH_THE_CONTENTS_OF_ALL_ENTITIES_DB_HAS_BEEN_RETURN);
        return ALL_ENTITY_DB;
    }

    public List<Object> getQuestInterview() {
        TreeSet<Integer> random = new TreeSet<>();
        if (ALL_QUESTION.isEmpty()) {
            getAllQuestion();
        }
        while (random.size() < LENGTH_INTERVIEW) {
            random.add(ThreadLocalRandom.current().nextInt(0, ALL_QUESTION.size()));
        }
        log.debug(CREATED_A_MAP_WITH_RANDOM_NUMBERS, random.size());

        List<Long> idQuest = new ArrayList<>(ALL_QUESTION.keySet());
        for (Integer integers : random) {
            Object aLong = idQuest.get(integers);
            QUEST_INTERVIEW.add(ALL_QUESTION.get(aLong));
        }
        log.debug(RECEIVED_MAP_WITH_INTERVIEW_QUESTIONS);
        return QUEST_INTERVIEW;
    }
}
