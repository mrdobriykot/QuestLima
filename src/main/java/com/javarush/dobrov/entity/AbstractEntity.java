package com.javarush.dobrov.entity;

import java.util.concurrent.atomic.AtomicLong;

public interface AbstractEntity {

    void setId(long id);
    Long getId();
}
