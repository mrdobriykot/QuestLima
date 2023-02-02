package com.javarush.quest.osypenko.service;

import com.javarush.quest.osypenko.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static com.javarush.quest.osypenko.costants.Constant.*;

public class TrainingService {
    public static final Logger log = LogManager.getLogger(TrainingService.class);

    public void trainingTable(HttpServletRequest request) {
        HashMap<Entity, Training> objectHashMap = new Util().getAllEntityDB();

        Map<String, String[]> parameterMap = request.getParameterMap();
        String parameter = null;
        for (String str : parameterMap.keySet()) {
            parameter = str;
        }
        log.debug(PUSH_BUTTON, parameter);

        for (Entity value : Entity.values()) {
            if (value.toString().equals(parameter)) {
                TreeMap<Long, DB> dbTreeMap = objectHashMap.get(value).getMap();
                Collection<DB> dbCollection = dbTreeMap.values();

                request.setAttribute(ATTRIBUTE, parameter);
                request.setAttribute(CONSTANT_ID, objectHashMap.get(value).getConstantID());
                request.setAttribute(DB_SIZE, dbCollection.size());
                request.setAttribute(DB_COLLECTION, dbCollection);
            }
        }
        log.debug(CREATED_TABLE, parameter);
    }
}
