package com.javarush.quest.osypenko.repository.entityDB;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.osypenko.repository.DB;
import com.javarush.quest.osypenko.repository.Training;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.TreeMap;

import static com.javarush.quest.osypenko.costants.Constant.*;

public class Core2 implements Training {
    public static final Logger log = LogManager.getLogger(Core2.class);
    private final TreeMap<Long, DB> map;
    private static final Long CONSTANT_ID = CORE_2_START_ID;
    private static Core2 core2;

    public static Core2 getInstance() {
        if (core2 == null) {
            core2 = new Core2();
        }
        return core2;
    }

    private Core2() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            //noinspection unchecked
            map = mapper.readValue(Core1.class.getResource(CORE_2_JSON), TreeMap.class);
            log.info(CREATE_MAP_IN_CLASS, this.getClass().getSimpleName());
        } catch (IOException e) {
            log.error(FAILED_TO_READ_THE_FILE_TO_GENERATE_THE_MAP);
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
