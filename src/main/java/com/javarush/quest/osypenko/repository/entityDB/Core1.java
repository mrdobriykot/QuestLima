package com.javarush.quest.osypenko.repository.entityDB;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.osypenko.costants.Constant;
import com.javarush.quest.osypenko.repository.DB;
import com.javarush.quest.osypenko.repository.Training;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.TreeMap;

public class Core1 implements Training {
    public static final Logger log = LogManager.getLogger(Core1.class);
    private final TreeMap<Long, DB> map;
    private static final Long CONSTANT_ID = Constant.CORE_1_START_ID;
    private static Core1 core1;

    public static Core1 getInstance() {
        if (core1 == null) {
            core1 = new Core1();
        }
        return core1;
    }

    private Core1() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            //noinspection unchecked
            map = mapper.readValue(Core1.class.getResource(Constant.CORE_1_JSON), TreeMap.class);
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
