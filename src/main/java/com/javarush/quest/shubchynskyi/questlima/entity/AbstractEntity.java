package com.javarush.quest.shubchynskyi.questlima.entity;

/**
 * Parent for any entity. Use as parent in wildcard
 */
public interface AbstractEntity {
    Long getId();

    void setId(Long id);
}
