package com.javarush.quest.osypenko.repository.entityDB;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.osypenko.costants.Constant;
import com.javarush.quest.osypenko.repository.DB;
import com.javarush.quest.osypenko.repository.Training;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.TreeMap;

public class Multithreading implements Training {
    public static final Logger log = LogManager.getLogger(Multithreading.class);
    private final TreeMap<Long, DB> map;
    private static final Long CONSTANT_ID = Constant.MULTITHREADING_START_ID;
    private static Multithreading multithreading;

    public static Multithreading getInstance() {
        if (multithreading == null) {
            multithreading = new Multithreading();
        }
        return multithreading;
    }

    private Multithreading() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            //noinspection unchecked
            map = mapper.readValue(Core1.class.getResource(Constant.MULTITHREADING_JSON), TreeMap.class);
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
