package com.javarush.quest.osypenko.service;

import com.javarush.quest.osypenko.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TrainingService {
    public static final Logger log = LogManager.getLogger(TrainingService.class);

    public void trainingTable(HttpServletRequest request) {
        HashMap<Entity, Training> objectHashMap = new Util().getAllEntityDB();

        Map<String, String[]> parameterMap = request.getParameterMap();
        String parameter = null;
        for (String str : parameterMap.keySet()) {
            parameter = str;
        }
        log.debug("Push button {}", parameter);


        for (Entity value : Entity.values()) {
            if (value.toString().equals(parameter)) {
                TreeMap<Long, DB> dbTreeMap = objectHashMap.get(value).getMap();
                Collection<DB> dbCollection = dbTreeMap.values();

                request.setAttribute("attribute", parameter);
                request.setAttribute("constantID", objectHashMap.get(value).getConstantID());
                request.setAttribute("dbSize", dbCollection.size());
                request.setAttribute("dbCollection", dbCollection);
            }
        }
        log.debug("Created table {}", parameter);
    }
}
