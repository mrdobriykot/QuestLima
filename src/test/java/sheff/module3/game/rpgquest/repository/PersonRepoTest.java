package sheff.module3.game.rpgquest.repository;

import sheff.module3.game.rpgquest.entity.Location;
import sheff.module3.game.rpgquest.entity.LocationKey;
import sheff.module3.game.rpgquest.entity.Person;
import sheff.module3.game.rpgquest.entity.Question;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepoTest {

    @Test
    void getPersonsByLocationId() {
        int locationId = 2;
        List<Person> persons = new ArrayList<>();

        Question question = Mockito.mock(Question.class);
        LocationKey key = Mockito.mock(LocationKey.class);
        IntStream.rangeClosed(1, 3)
                .forEach((num) -> persons.add(new Person(
                        num, "text" + num, new Location(num, "name" + num, false), question, key))
                );

        PersonRepo personRepo = new PersonRepo();
        for (Person person : persons) {
            personRepo.save(person.getId(), person);
        }

        List<Person> expected = persons.stream()
                .filter(v -> v.getLocation().getId() == locationId)
                .toList();

        List<Person> actual = personRepo.getPersonsByLocationId(locationId);

        assertEquals(expected, actual);
    }
}
