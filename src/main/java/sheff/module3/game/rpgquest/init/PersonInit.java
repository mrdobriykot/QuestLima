package sheff.module3.game.rpgquest.init;

import sheff.module3.game.rpgquest.entity.Person;
import sheff.module3.game.rpgquest.repository.LocationKeyRepo;
import sheff.module3.game.rpgquest.repository.LocationRepo;
import sheff.module3.game.rpgquest.repository.QuestionRepo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class PersonInit {
    @Getter
    private final List<Person> persons = new ArrayList<>();

    public PersonInit(LocationRepo locationRepo, QuestionRepo questionRepo, LocationKeyRepo keyRepo) {

        persons.add(new Person(1, "Trekon", locationRepo.getById(1), questionRepo.getById(1), keyRepo.getById(1)));
        persons.add(new Person(2, "Tibost", locationRepo.getById(3), questionRepo.getById(7), keyRepo.getById(2)));
        persons.add(new Person(3, "Faramond", locationRepo.getById(5), questionRepo.getById(13), keyRepo.getById(3)));
        persons.add(new Person(4, "Gaglus", locationRepo.getById(7), questionRepo.getById(19), keyRepo.getById(6)));
        persons.add(new Person(5, "Rineamit", locationRepo.getById(8), questionRepo.getById(37), keyRepo.getById(7)));
        persons.add(new Person(6, "Hudd", locationRepo.getById(9), questionRepo.getById(25), keyRepo.getById(5)));
        persons.add(new Person(7, "Reala", locationRepo.getById(11), questionRepo.getById(31), keyRepo.getById(8)));
        persons.add(new Person(8, "Holren", locationRepo.getById(12), questionRepo.getById(43), keyRepo.getById(4)));
        persons.add(new Person(9, "Linetta", locationRepo.getById(15), questionRepo.getById(49), keyRepo.getById(9)));
    }
}
