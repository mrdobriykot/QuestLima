package com.javarush.khmelov.controller.cmd.impl;

import com.javarush.khmelov.controller.cmd.Command;
import com.javarush.khmelov.view.View;
import com.javarush.khmelov.dto.UserTo;
import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.view.Go;
import com.javarush.khmelov.controller.Key;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Collection;

@Controller(Go.USERS)
@AllArgsConstructor
public class UsersCommand implements Command {

    private final UserService userService;

    @Override
    public View get(HttpServletRequest request) throws IOException, ServletException {
        Collection<UserTo> users = userService.getAll();
        request.setAttribute(Key.USERS, users);
        return View.forward(Key.USERS);
    }
}