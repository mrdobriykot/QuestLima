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
public class Quest extends AbstractEntity {
    private long authorId;
    private String name;
    private long currentEventId;
    private Hero hero;
}
