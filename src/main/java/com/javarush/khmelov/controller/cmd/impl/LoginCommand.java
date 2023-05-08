package com.javarush.khmelov.controller.cmd.impl;

import com.javarush.khmelov.controller.Key;
import com.javarush.khmelov.controller.cmd.Command;
import com.javarush.khmelov.dto.UserTo;
import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.view.Go;
import com.javarush.khmelov.view.View;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Optional;

@Controller(Go.LOGIN)
@AllArgsConstructor
public class LoginCommand implements Command {

    private final UserService userService;

    @Override
    public View get(HttpServletRequest request) throws ServletException, IOException {
        return View.forward(Go.LOGIN);
    }

    @Override
    public View post(HttpServletRequest request) {
        String login = request.getParameter(Key.LOGIN);
        String password = request.getParameter(Key.PASSWORD);
        Optional<UserTo> user = userService.get(login, password);
        if (user.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute(Key.USER, user.get());
            return View.redirect(Go.PROFILE);
        } else {
            return View.redirect(Go.LOGIN); //todo add error message
        }
    }
}
