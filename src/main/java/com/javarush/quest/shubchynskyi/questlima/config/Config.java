package com.javarush.quest.shubchynskyi.questlima.config;

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

    public final Path WEB_INF = Paths.get(URI.create(
                    Objects.requireNonNull(
                            Config.class.getResource(Key.SLASH_SIGN)
                    ).toString()))
            .getParent();

}
