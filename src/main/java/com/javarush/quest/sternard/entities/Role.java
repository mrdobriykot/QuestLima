package com.javarush.quest.sternard.entities;

import lombok.extern.slf4j.Slf4j;

import static com.javarush.quest.sternard.util.message.LoggerMessages.ROLE_NOT_SUPPORTED_EXCEPTION_ID;

@Slf4j
public enum Role {
    GUEST(4L), EDITOR(3L), USER(2L), ADMIN(1L);

    private final Long id;

    Role(Long id) {
        this.id = id;
    }

    public static Role getRole(Long id) { // crutches for hibernate only
        if (id == 1L)
            return Role.ADMIN;
        else if (id == 2L)
            return Role.USER;
        else if (id == 3L)
            return Role.EDITOR;
        else if (id == 4L)
            return Role.GUEST;

        log.error(ROLE_NOT_SUPPORTED_EXCEPTION_ID, id);
        throw new IllegalArgumentException(id + " not supported.");
    }

    public Long getId() {
        return id;
    }
}
