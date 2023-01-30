package com.javarush.quest.ivanilov.entities.game;

import com.javarush.quest.ivanilov.entities.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Event extends AbstractEntity {
    private long questId;
    private String name;
    private String message;
    private EventType eventType;
    private Task task;
}
