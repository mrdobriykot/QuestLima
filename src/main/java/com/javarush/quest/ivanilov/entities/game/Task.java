package com.javarush.quest.ivanilov.entities.game;

import com.javarush.quest.ivanilov.entities.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Builder
@ToString
public class Task extends AbstractEntity {
    private String description;
    private Hero villain;
    private TaskType type;
    private Map<Integer, String> Options;
    private Map<Integer, Event> Results;
    private Event nextEventIfFightWasWon;
    private Event nextEventIfFightWasLost;
}
