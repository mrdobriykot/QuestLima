package sheff.module3.game.rpgquest.repository;

import sheff.module3.game.rpgquest.entity.Location;
import sheff.module3.game.rpgquest.init.LocationInit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class LocationRepoTest {

    @Test
    void getLocationByIdDefault() {
        int locationId = 2;
        List<Location> locations = new ArrayList<>();

        IntStream.rangeClosed(1, 3)
                .forEach((num) -> locations.add(new Location(num, "text" + num, false)));

        LocationInit locationInit = new LocationInit();
        LocationRepo locationRepo = new LocationRepo(locationInit);
        for (Location location : locations) {
            locationRepo.save(location.getId(), location);
        }

        Location expected = locationInit.getLocations().stream()
                .filter(userLocation -> userLocation.getId() == locationId)
                .findFirst()
                .get();

        Location actual = locationRepo.getLocationById(locationId);

        assertEquals(expected, actual);
    }
}
