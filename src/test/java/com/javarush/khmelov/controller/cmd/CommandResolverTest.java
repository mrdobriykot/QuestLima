package com.javarush.khmelov.controller.cmd;

import com.javarush.khmelov.config.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CommandResolverTest {

    CommandResolver commandResolver = Context.getBean(CommandResolver.class);

    @Test
    void resolve() {
        Command profile = commandResolver.resolve("/profile");
        Assertions.assertEquals("PROFILE", profile);
    }
}