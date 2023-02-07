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
public class Hero extends AbstractEntity implements Cloneable {
    public static final int MIN_HIT_POWER = 1;
    private String name;
    private int health;
    private int strength;

    public int hit(Hero target) {
        int power = ThreadLocalRandom.current().nextInt(MIN_HIT_POWER, strength + 1);
        target.health -= power;
        return power;
    }

    @Override
    public Hero clone() throws CloneNotSupportedException {
        return (Hero) super.clone();
    }
}
