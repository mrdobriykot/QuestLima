package com.javarush.quest.ivanilov.entities.game;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Option {
    private int id;
    private long eventId;
    private String name;
}
