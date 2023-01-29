package sheff.module3.game.rpgquest.repository;

import java.util.HashMap;
import java.util.Map;


public class Repository<K, T> {

    public Map<K, T> repo = new HashMap<>();

    public void save(K id, T entity) {
        repo.put(id, entity);
    }

    public T getById(K id) {
        return repo.get(id);
    }
}
