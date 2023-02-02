package com.javarush.quest.ivanilov.config;

import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.*;
import com.javarush.quest.ivanilov.utils.Json;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Messages;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public class Config {

    QuestParser questParser;
    UserService userService;

    public void readUsersAndQuestsFromResources() {
                try {
                    Json<User[]> userReader = new Json<>(User[].class);
                    Json<String[]> scenarioReader = new Json<>(String[].class);
                    User[] users = userReader.readJson(Attributes.USERS_JSON);
                    Arrays.stream(users).forEach(this::initUser);
                    String[] strings = scenarioReader.readJson(Attributes.QUESTS_JSON);
                    Arrays.stream(strings).forEach(questParser::parse);
                } catch (Exception e) {
                    throw new RuntimeException(Messages.INIT_PROBLEM);
                }
            }

    private void initUser(User user) {
        userService.createOrModifyUser(user.getLogin(), user.getPassword(), user.getRole(), null);
    }

}
