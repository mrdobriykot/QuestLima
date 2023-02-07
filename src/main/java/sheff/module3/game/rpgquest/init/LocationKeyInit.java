package sheff.module3.game.rpgquest.init;

import sheff.module3.game.rpgquest.entity.LocationKey;
import sheff.module3.game.rpgquest.repository.LocationRepo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class LocationKeyInit {
    @Getter
    private final List<LocationKey> locationKeys = new ArrayList<>();

    public LocationKeyInit(LocationRepo locationRepo) {

        locationKeys.add(new LocationKey(1, locationRepo.getById(2)));
        locationKeys.add(new LocationKey(2, locationRepo.getById(5)));
        locationKeys.add(new LocationKey(3, locationRepo.getById(6)));
        locationKeys.add(new LocationKey(4, locationRepo.getById(15)));
        locationKeys.add(new LocationKey(5, locationRepo.getById(8)));
        locationKeys.add(new LocationKey(6, locationRepo.getById(10)));
        locationKeys.add(new LocationKey(7, locationRepo.getById(13)));
        locationKeys.add(new LocationKey(8, locationRepo.getById(12)));
        locationKeys.add(new LocationKey(9, locationRepo.getById(14)));
    }
}
