package com.javarush.quest.osypenko.repository;

import java.util.HashMap;

public interface Training {
    HashMap<Long, DB> map = new HashMap<>();

    Long getConstantID();

    HashMap<Long, DB> getMap();

}
