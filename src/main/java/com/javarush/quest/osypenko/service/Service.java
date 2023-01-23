package com.javarush.quest.osypenko.service;

import com.javarush.quest.osypenko.repository.*;
import com.javarush.quest.osypenko.repository.entity.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Service {
    private static final HashMap<Entity, Training> objectHashMap = new HashMap<>();

    public static void extracted(HttpServletRequest request) {
        objectHashMap.put(Entity.CORE1, new Core1());
        objectHashMap.put(Entity.CORE2, new Core2());
        objectHashMap.put(Entity.MULTITHREADING, new Multithreading());
        objectHashMap.put(Entity.ALGORITHMS, new Algorithms());
        objectHashMap.put(Entity.PATTERNS, new Patterns());

        Map<String, String[]> parameterMap = request.getParameterMap();
        String parameter = null;
        for (String str : parameterMap.keySet()) {
            parameter = str;
        }
        request.setAttribute("attribute", parameter);

        for (Entity value : Entity.values()) {
            if (value.toString().equals(parameter)) {
                HashMap<Long, DB> dbHashMap = objectHashMap.get(value).getMap();
                TreeMap<Long, DB> sortedMap = new TreeMap<>(dbHashMap);
                Collection<DB> values = sortedMap.values();
                request.setAttribute("constantID", objectHashMap.get(value).getConstantID());
                request.setAttribute("size", values.size());
                request.setAttribute("values", values);
            }
        }
    }
}
