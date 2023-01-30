package com.javarush.quest.ivanilov.services;


import com.javarush.quest.ivanilov.entities.game.Hero;
import com.javarush.quest.ivanilov.repositories.HeroRepository;
import com.javarush.quest.ivanilov.repositories.Repository;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Optional;

@Slf4j
public enum HeroService {

    HERO_SERVICE;

    private final Repository<Hero> repo = new HeroRepository();

    public Collection<Hero> getAll() {
        return repo.getAll();
    }

    public Hero create(Hero hero) {
        return repo.create(hero);
    }

    public Hero get(long id) {
        return repo.get(id);
    }

    public Hero update(Hero hero) {
        return repo.update(hero);
    }

    public void delete(Hero hero) {
        repo.delete(hero);
    }

    public Optional<Hero> find(String pattern) {
        return repo.find(pattern);
    }
}
