package com.javarush.quest.osypenko.repository.entityDB;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.osypenko.costants.Constant;
import com.javarush.quest.osypenko.repository.DB;
import com.javarush.quest.osypenko.repository.Training;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.TreeMap;

public class Patterns implements Training {
    public static final Logger log = LogManager.getLogger(Patterns.class);
    private final TreeMap<Long, DB> map;
    private static final Long CONSTANT_ID = Constant.PATTERNS_START_ID;
    private static Patterns patterns;
    public static Patterns getInstance() {
        if (patterns == null) {
            patterns = new Patterns();
        }
        return patterns;
    }

    private Patterns() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            //noinspection unchecked
            map = mapper.readValue(Core1.class.getResource(Constant.PATTERNS_JSON), TreeMap.class);
            log.info("Create map in class {}", this.getClass().getSimpleName());
        } catch (IOException e) {
            log.error("Failed to read the file to generate the map");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getConstantID() {
        return CONSTANT_ID;
    }

    @Override
    public TreeMap<Long, DB> getMap() {
        return map;
    }

}
