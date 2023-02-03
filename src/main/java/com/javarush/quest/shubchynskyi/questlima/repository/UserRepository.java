package com.javarush.quest.shubchynskyi.questlima.repository;

import com.javarush.quest.shubchynskyi.questlima.entity.user.Role;
import com.javarush.quest.shubchynskyi.questlima.entity.user.User;
import com.javarush.quest.shubchynskyi.questlima.repository.abstract_repo.BaseRepository;

import java.util.stream.Stream;

/**
 * repository for Users, must be changed in future
 */
public class UserRepository extends BaseRepository<User> {

    public UserRepository() { // TODO перенести в конфиг / сделать инит через json
        create(new User(-1L, "admin", "admin", Role.ADMIN));
        create(new User(-1L, "guest", "guest", Role.GUEST));
        create(new User(-1L, "moderator", "moderator", Role.MODERATOR));
        create(new User(-1L, "user1", "user1", Role.USER));
        create(new User(-1L, "user2", "user2", Role.USER));
        create(new User(-1L, "user3", "user3", Role.USER));
    }

    @Override
    public Stream<User> find(User pattern) {
        return map.values()
                .stream()
                .filter(user -> nullOrEquals(pattern.getId(), user.getId()))
                .filter(user -> nullOrEquals(pattern.getLogin(), user.getLogin()))
                .filter(user -> nullOrEquals(pattern.getPassword(), user.getPassword()))
                .filter(user -> nullOrEquals(pattern.getRole(), user.getRole()));
    }

}
