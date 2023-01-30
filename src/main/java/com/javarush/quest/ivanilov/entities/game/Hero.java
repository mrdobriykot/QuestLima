package com.javarush.quest.ivanilov.entities.game;

import com.javarush.quest.ivanilov.entities.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@Builder
@ToString
public class Hero extends AbstractEntity {
    public static final int MIN_HIT_POWER = 1;
    private String name;
    private int health;
    private int strength;

    public int hit(Hero hero) {
        int power = ThreadLocalRandom.current().nextInt(MIN_HIT_POWER, strength + 1);
        hero.health -= power;
        return power;
    }
}
