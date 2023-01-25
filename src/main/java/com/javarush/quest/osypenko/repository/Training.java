package com.javarush.quest.osypenko.repository;

import java.util.TreeMap;

public interface Training {
    TreeMap<Long, DB> map = new TreeMap<>();

    Long getConstantID();

    TreeMap<Long, DB> getMap();

}
