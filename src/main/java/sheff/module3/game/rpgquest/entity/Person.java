package sheff.module3.game.rpgquest.entity;

import lombok.Getter;

@Getter
public class Person {
    private final int id;
    private final String name;
    private final Location location;
    private final Question startQuestion;
    private LocationKey key;

    public Person(int id, String name, Location location, Question startQuestion) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.startQuestion = startQuestion;
    }

    public Person(int id, String name, Location location, Question startQuestion, LocationKey key) {
        this(id, name, location, startQuestion);
        this.key = key;
    }
}
