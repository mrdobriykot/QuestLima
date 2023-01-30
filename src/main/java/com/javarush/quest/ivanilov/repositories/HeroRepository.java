package com.javarush.quest.ivanilov.repositories;

import com.javarush.quest.ivanilov.entities.game.Hero;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Optional;

public class HeroRepository extends BaseRepository<Hero> {
    @Override
    public Optional<Hero> find(String pattern) {
        throw new NotImplementedException();
    }
}
