package sheff.module3.game.rpgquest.entity;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LocationKeyTest {

    @Test
    void getId() {
        assertEquals(5, new LocationKey(5, Mockito.mock(Location.class)).getId());
    }

    @Test
    void getLocation() {
        Location location = new Location(3, "name", true);
        assertEquals(location, new LocationKey(5, location).getLocation());
    }
}
