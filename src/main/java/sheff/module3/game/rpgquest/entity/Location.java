package sheff.module3.game.rpgquest.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Location {
    private final int id;
    private final String name;
    private final List<Location> locationsForMovement;
    @Setter
    private boolean block;

    public Location(int id, String name, boolean block) {
        this.id = id;
        this.name = name;
        this.block = block;
        locationsForMovement = new ArrayList<>();
    }
}
