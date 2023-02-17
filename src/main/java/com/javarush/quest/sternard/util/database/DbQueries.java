package com.javarush.quest.sternard.util.database;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DbQueries {
    public static final String MYSQL_UPDATE_USER = """
            UPDATE users
            SET
                login = ?,
                password = ?,
                profile_image = ?,
                played_quests_count = ?,
                role_id = (SELECT id FROM roles r WHERE r.role = ?)
            WHERE id = ?
            """;

    public static final String MYSQL_CREATE_USER = """
            INSERT INTO users
            (login, password, profile_image, played_quests_count, role_id)
            VALUES
            (?, ?, ?, ?, (SELECT id FROM roles WHERE role=?))
            """;

    public static final String MYSQL_DELETE_USER = "delete FROM users WHERE id=?";

    public static final String MYSQL_SELECT_ALL_USERS = """
            SELECT users.id as id,
                   login,
                   password,
                   profile_image,
                   played_quests_count,
                   role
            FROM users
            JOIN
            roles role
            ON users.role_id = role.id;
            """;

    public static final String MYSQL_GET_QUESTIONS_BY_QUEST_ID = """
            SELECT
            questions.id,
                quest_id,
                image,
                text,
                state
            FROM questions
            JOIN quest_states
            ON questions.quest_state_id = quest_states.id
            WHERE quest_id=?
            """;
    public static final String MYSQL_GET_ANSWERS_BY_QUESTION_ID = "SELECT * FROM answers WHERE question_id=?";
    public static final String MYSQL_GET_ALL_QUESTS = """
            SELECT quests.id,
                   title,
                   description,
                   image,
                   views,
                   rating,
                   first_question_id,
                   (SELECT login FROM users WHERE users.id=author_id) as author,
                   creation_date FROM quests
            """;

    public static final String MYSQL_UPDATE_QUEST = """
            UPDATE quests
            SET
                title=?,
                description=?,
                image=?,
                creation_date=?,
                views=?,
                rating=?,
                author_id=(SELECT id FROM users WHERE login=?)
            WHERE id=?
            """;

    public static final String MYSQL_DELETE_QUEST = "DELETE FROM quests WHERE id=?";
}