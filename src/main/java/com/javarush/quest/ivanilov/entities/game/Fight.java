package com.javarush.quest.ivanilov.entities.game;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Fight {
    private long eventId;
    private long taskId;
    private Hero hero;
    private Hero villain;
    private EventType status;
    private List<String> hitOptions;
    private List<String> blockOptions;
    private List<String> fightLog;
}
