package com.javarush.quest.ivanilov.services;


import com.javarush.quest.ivanilov.entities.game.Hero;
import com.javarush.quest.ivanilov.repositories.HeroRepository;
import com.javarush.quest.ivanilov.repositories.Repository;

public enum HeroService {

    HERO_SERVICE;

    private final Repository<Hero> repo = new HeroRepository();

    public Hero create(Hero hero) {
        return repo.create(hero);
    }

    public Hero get(long id) {
        return repo.get(id);
    }

    public Hero update(Hero hero) {
        return repo.update(hero);
    }
}
