package com.javarush.quest.shubchynskyi.questlima.config;

import com.javarush.quest.shubchynskyi.questlima.entity.user.Role;
import com.javarush.quest.shubchynskyi.questlima.entity.user.User;
import com.javarush.quest.shubchynskyi.questlima.repository.*;
import com.javarush.quest.shubchynskyi.questlima.util.Key;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public enum Config {

    CONFIG;

    public final UserRepository userRepository = new UserRepository();
    public final QuestRepository questRepository = new QuestRepository();
    public final QuestionRepository questionRepository = new QuestionRepository();
    public final AnswerRepository answerRepository = new AnswerRepository();

     { // to json
        userRepository.create(new User(-1L, "admin", "admin", Role.ADMIN));
        userRepository.create(new User(-1L, "guest", "guest", Role.GUEST));
        userRepository.create(new User(-1L, "moderator", "moderator", Role.MODERATOR));
        userRepository.create(new User(-1L, "user1", "user1", Role.USER));
        userRepository.create(new User(-1L, "user2", "user2", Role.USER));
        userRepository.create(new User(-1L, "user3", "user3", Role.USER));
    }


    public final Path WEB_INF = Paths.get(URI.create(
                    Objects.requireNonNull(
                            Config.class.getResource(Key.REGEX_SLASH_SIGN)
                    ).toString()))
            .getParent();

}
