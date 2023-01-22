package com.javarush.quest.osypenko.repository;

import java.util.HashMap;

public interface Training {
    HashMap<Long, DB> map = new HashMap<>();

    HashMap<Long, DB> getMap();

}
