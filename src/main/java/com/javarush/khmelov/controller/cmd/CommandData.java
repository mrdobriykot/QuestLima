package com.javarush.khmelov.controller.cmd;

import com.javarush.khmelov.controller.cmd.impl.*;

import static com.javarush.khmelov.config.Context.getBean;

enum CommandData {

//    //replace enum -> scan package or beans
    CREATE_QUEST(getBean(CreateQuestCommand.class)),
    GAME(getBean(GameCommand.class)),
    INDEX(getBean(IndexCommand.class)),
    LOGIN(getBean(LoginCommand.class)),
    LOGOUT(getBean(LogoutCommand.class)),
    PROFILE(getBean(ProfileCommand.class)),
    QUEST(getBean(QuestCommand.class)),
    SIGNUP(getBean(SignupCommand.class)),
    USER(getBean(UserCommand.class)),
    USERS(getBean(UsersCommand.class));

    final Command command;

    CommandData(Command command) {
        this.command = command;
    }


}
