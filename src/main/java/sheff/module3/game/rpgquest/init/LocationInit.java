package sheff.module3.game.rpgquest.init;

import sheff.module3.game.rpgquest.entity.Location;
import lombok.Getter;

import java.util.*;

public class LocationInit {
    @Getter
    private final List<Location> locations = new ArrayList<>();

    public LocationInit() {

        Location chamoia = new Location(1, "Chamoia", false);
        Location kunszalia = new Location(2, "Kunszalia", true);
        Location nyirmisia = new Location(3, "Nyirmisia", false);
        Location freitileria = new Location(4, "Freitileria", false);
        Location olbore = new Location(5, "Olbore", true);
        Location alfnirland = new Location(6, "Alfnirland", true);
        Location holunland = new Location(7, "Holunland", false);
        Location seiratelia = new Location(8, "Seiratelia", true);
        Location dagalhia = new Location(9, "Dagalhia", false);
        Location abrenus = new Location(10, "Abrenus", true);
        Location thesia = new Location(11, "Thesia", false);
        Location anolaria = new Location(12, "Anolaria", true);
        Location nolahmaa = new Location(13, "Nolahmaa", true);
        Location akosia = new Location(14, "Akosia", true);
        Location krismaa = new Location(15, "Krismaa", true);

        chamoia.getLocationsForMovement().addAll(Arrays.asList(freitileria, alfnirland, kunszalia));
        kunszalia.getLocationsForMovement().addAll(Arrays.asList(chamoia, alfnirland, nyirmisia));
        nyirmisia.getLocationsForMovement().addAll(Arrays.asList(kunszalia, abrenus));
        freitileria.getLocationsForMovement().addAll(Arrays.asList(chamoia, alfnirland, olbore));
        olbore.getLocationsForMovement().addAll(Arrays.asList(freitileria, alfnirland));
        alfnirland.getLocationsForMovement().addAll(Arrays.asList(olbore, freitileria, chamoia, kunszalia, holunland));
        holunland.getLocationsForMovement().add(alfnirland);
        seiratelia.getLocationsForMovement().addAll(Arrays.asList(dagalhia, abrenus));
        dagalhia.getLocationsForMovement().addAll(Arrays.asList(seiratelia, abrenus));
        abrenus.getLocationsForMovement().addAll(Arrays.asList(dagalhia, seiratelia, thesia, nolahmaa));
        thesia.getLocationsForMovement().add(abrenus);
        anolaria.getLocationsForMovement().addAll(Arrays.asList(akosia, nolahmaa));
        nolahmaa.getLocationsForMovement().addAll(Arrays.asList(anolaria, abrenus, krismaa));
        akosia.getLocationsForMovement().add(anolaria);
        krismaa.getLocationsForMovement().add(nolahmaa);

        locations.add(chamoia);
        locations.add(kunszalia);
        locations.add(nyirmisia);
        locations.add(freitileria);
        locations.add(olbore);
        locations.add(alfnirland);
        locations.add(holunland);
        locations.add(seiratelia);
        locations.add(dagalhia);
        locations.add(abrenus);
        locations.add(thesia);
        locations.add(anolaria);
        locations.add(nolahmaa);
        locations.add(akosia);
        locations.add(krismaa);
    }
}
