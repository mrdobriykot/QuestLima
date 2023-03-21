DROP TABLE IF EXISTS answer;

DROP TABLE IF EXISTS question;

DROP TABLE IF EXISTS quest;

DROP TABLE IF EXISTS game;

DROP TABLE IF EXISTS user_info;

DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS role;

DROP TABLE IF EXISTS game_state;

-- -----------------------------------------------------
-- Table role
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS role
(
    value VARCHAR(128) NOT NULL,
    PRIMARY KEY (value)
);


-- -----------------------------------------------------
-- Table users
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL,
    login    VARCHAR(128) NULL,
    password VARCHAR(256) NULL,
    role     VARCHAR(128) NOT NULL,
    PRIMARY KEY (id),


    CONSTRAINT fk_users_role
        FOREIGN KEY (role)
            REFERENCES role (value)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
);

CREATE TABLE user_info
(
    id      BIGSERIAL PRIMARY KEY,
    address VARCHAR(128),
    phone   VARCHAR(128),
    user_id BIGINT REFERENCES users
);

-- -----------------------------------------------------
-- Table quest
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS quest
(
    id                BIGSERIAL,
    name              VARCHAR(512)  NULL,
    text              VARCHAR(2048) NULL,
    start_question_id INT           NULL,
    users_id          INT           NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_quest_users
        FOREIGN KEY (users_id)
            REFERENCES users (id)
            ON DELETE SET NULL
            ON UPDATE SET NULL
);


-- -----------------------------------------------------
-- Table game_state
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS game_state
(
    value VARCHAR(64) NOT NULL,
    PRIMARY KEY (value)
);


-- -----------------------------------------------------
-- Table question
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS question
(
    id         BIGSERIAL,
    text       VARCHAR(256) NULL,
    quest_id   INT          NOT NULL,
    game_state VARCHAR(64)  NOT NULL,
    PRIMARY KEY (id),


    CONSTRAINT fk_question_quest
        FOREIGN KEY (quest_id)
            REFERENCES quest (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT fk_question_game_state
        FOREIGN KEY (game_state)
            REFERENCES game_state (value)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table answer
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS answer
(
    id               BIGSERIAL,
    text             VARCHAR(256) NULL,
    next_question_id INT          NULL,
    question_id      INT          NOT NULL,
    PRIMARY KEY (id),

    CONSTRAINT fk_answer_question
        FOREIGN KEY (question_id)
            REFERENCES question (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Table game
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS game
(
    id                  BIGSERIAL,
    quest_id            INT         NULL,
    current_question_id INT         NULL,
    users_id            INT         NOT NULL,
    game_state          VARCHAR(64) NOT NULL,
    PRIMARY KEY (id),


    CONSTRAINT fk_game_users
        FOREIGN KEY (users_id)
            REFERENCES users (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT fk_game_game_state
        FOREIGN KEY (game_state)
            REFERENCES game_state (value)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);



-- -----------------------------------------------------
-- Data for table role
-- -----------------------------------------------------
START TRANSACTION;

INSERT INTO role (value)
VALUES ('ADMIN'),
       ('USER'),
       ('GUEST');

COMMIT;


-- -----------------------------------------------------
-- Data for tables
-- -----------------------------------------------------
START TRANSACTION;

INSERT INTO users (id, login, password, role)
VALUES (DEFAULT, 'admin', '456', 'ADMIN'),
       (DEFAULT, 'ivan', '123', 'USER'),
       (DEFAULT, 'guest', '789', 'GUEST');

INSERT INTO user_info(address, phone, user_id)
VALUES ('Urban street 11 23', '+380 (50) 123-45-67', 1),
       ('Lenina 22 34', '+7 (095) 123-45-67', 2),
       ('Very 33 45', '+380 (50) 123-45-67', 3);

INSERT INTO game_state (value)
VALUES ('PLAY'),
       ('WIN'),
       ('LOST');

INSERT INTO quest(users_id, name, text, start_question_id)
VALUES (1, 'Проверим арифметику', 'исходный текст квеста', -1),
       (1, 'Второй квест', 'исходный текст второго квеста', -1),
       (1, 'Третий квест', 'исходный текст третьего квеста', -1);

INSERT INTO question(quest_id, text, game_state)
VALUES (1, 'Знаешь арифметику?', 'PLAY'),                                       --1
       (1, 'Сколько будет дважды два?', 'PLAY'),                                --2
       (1, 'Эх... это проигрыш. Надо мне было лучше учиться в школе.', 'LOST'), --3
       (1, 'Ура, победа в сложнейшем квесте!!!', 'WIN'); --4

INSERT INTO answer(question_id, text, next_question_id)
VALUES (1, 'Да, конечно', 2),
       (1, 'А что это такое?', 3),
       (2, 'Один', 3),
       (2, 'Два', 3),
       (2, 'Три', 3),
       (2, 'Четыре', 4);

INSERT INTO game(quest_id, users_id, current_question_id, game_state)
VALUES (1, 1, 1, 'PLAY'),
       (1, 1, NULL, 'WIN'),
       (1, 1, NULL, 'WIN'),
       (1, 1, NULL, 'LOST'),
       (1, 2, 1, 'PLAY'),
       (1, 2, NULL, 'WIN');
COMMIT;




