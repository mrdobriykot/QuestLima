create table answer
(
    id   bigserial,
    text varchar(2048)
);

alter table answer
    owner to postgres;

create table question
(
    id               bigserial
        primary key,
    text             varchar(256),
    question_id      bigint,
    next_question_id bigint
);

alter table question
    owner to postgres;
