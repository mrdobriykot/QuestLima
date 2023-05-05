package com.javarush.khmelov.controller.cmd;

import org.springframework.stereotype.Component;

import static com.javarush.khmelov.controller.cmd.CommandData.INDEX;

@Component
public class CommandResolver {
    public Command resolve(String uri) {
        String cmd = uri
                .replace("/", "")
                .replace("-", "_")
                .replaceAll("\\?.*", "")
                .toUpperCase();
        return (cmd.isBlank())
                ? INDEX.command
                : CommandData.valueOf(cmd).command;
    }
}
