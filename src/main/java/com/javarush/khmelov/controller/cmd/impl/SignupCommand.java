package com.javarush.khmelov.controller.cmd.impl;

import com.javarush.khmelov.controller.cmd.Command;
import com.javarush.khmelov.view.View;
import com.javarush.khmelov.dto.UserTo;
import com.javarush.khmelov.service.ImageService;
import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.view.Go;
import com.javarush.khmelov.controller.Key;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Optional;

@Controller(Go.SIGNUP)
@AllArgsConstructor
public class SignupCommand implements Command {

    private final UserService userService;
    private final ImageService imageService;

//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        config.getServletContext().setAttribute(Key.ROLES, Role.values());
//        super.init(config);
//    }

    @Override
    public View get(HttpServletRequest request) throws ServletException, IOException {
        return View.forward(Go.SIGNUP);
    }

    @Override
    public View post(HttpServletRequest request) throws ServletException, IOException {
        String login = request.getParameter(Key.LOGIN);
        String password = request.getParameter(Key.PASSWORD);

        Optional<UserTo> userTo = userService.create(login, password);
        imageService.uploadImage(request, userTo.orElseThrow().getImage());
        return View.redirect(Go.USERS);
    }
}
