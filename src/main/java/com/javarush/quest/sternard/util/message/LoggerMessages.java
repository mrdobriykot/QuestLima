package com.javarush.quest.sternard.util.message;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LoggerMessages {
    public static final String BAD_PARAMETERS = "Parameters do not correspond to the necessary: {}";
    public static final String USER_SUBSTITUTION_PARAMETERS = "There was a substitution of the ID: {}, user not founded";
    public static final String USER_WAS_NOT_UPDATED = "User was not updated. Login is busy or entered bad parameters. User ({}:{})";
    public static final String USER_WAS_UPDATED = "User was updated, user: ({}:{})";
    public static final String USER_PERMISSION_DENIED_TO_PAGE = "User tried to go to the page to which he should not have passed: {}";
    public static final String USER_WAS_DELETED = "User {}, was deleted";
    public static final String USER_NOT_FOUND_OR_INCORRECT_FORM_DATA = "User not found or incorrect login/password. Login: {}";
    public static final String USER_IS_LOGGED_NOW = "User {} is logged now";
    public static final String QUEST_SUBSTITUTION_ID_OR_NEXT_QUESTION_ID = "Quest substitution questId ({}) or nextQuestionId ({}) by null";
    public static final String CHANGE_REPOSITORY_USERS_AND_QUESTS = "Change repository users {} and quests {}";
    public static final String UPDATED_VIEWS_QUEST = "Updated views quest id {} with user id {}";
    public static final String UPDATED_PLAYED_QUEST_COUNT = "Updated played quest count at user id {} and quest id {}";
    public static final String UPDATED_RATING_IN_QUEST = "Updated rating in quest. Quest id: {}";
    public static final String USER_START_PLAYING_IN_QUEST = "User {} start playing in quest. Quest id: {}";
    public static final String QUEST_WAS_DELETED = "Quest {}, was deleted";
    public static final String QUEST_WAS_UPDATED = "Quest was updated, quest id: {}";
    public static final String QUEST_SUBSTITUTION_PARAMETERS = "There was a substitution of the ID: {}, quest not founded";
    public static final String QUESTION_SUBSTITUTION_PARAMETERS = "There was a substitution of the ID: {}, question not founded";
    public static final String QUEST_WRONG_PARAMETERS_OR_DENIED_SYMBOLS = "Quest not updated, quest not found or wrong symbols used in text. ID: {}";
    public static final String THE_USER_IS_EXIST_ON_REGISTER_PAGE = "The user is exist {} on {} page";
    public static final String USER_WITH_THIS_ID_IS_EXIST = "User with this id is exist : {}";
    public static final String ROLE_NOT_SUPPORTED_EXCEPTION_ID = "Role not supported exception, id: {}";
    public static final String DOES_NOT_EXIST = "{} does not exist!";
    public static final String ERROR_UPDATING_SETTINGS_FROM_JSON = "Error updating settings from json: {}";
}
