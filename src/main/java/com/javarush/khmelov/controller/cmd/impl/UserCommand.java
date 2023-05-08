package com.javarush.khmelov.controller.cmd.impl;

import com.javarush.khmelov.controller.Key;
import com.javarush.khmelov.controller.cmd.Command;
import com.javarush.khmelov.dto.UserTo;
import com.javarush.khmelov.service.ImageService;
import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.view.Go;
import com.javarush.khmelov.view.View;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Controller(Go.USER)
@AllArgsConstructor
public class UserCommand implements Command {

    private final UserService userService;
    private final ImageService imageService;

    @Override
    public View get(HttpServletRequest request) throws ServletException, IOException {
        String parameterId = request.getParameter(Key.ID);
        request.setAttribute(Key.ID, parameterId);
        if (Objects.nonNull(parameterId)) {
            long id = Long.parseLong(parameterId);
            Optional<UserTo> optionalUser = userService.get(id);
            if (optionalUser.isPresent()) {
                UserTo user = optionalUser.get();
                request.setAttribute(Key.USER, user);
            }
            return View.forward(Go.USER);
        }
        return View.redirect(Go.USERS);
    }

    @Override
    public View post(HttpServletRequest request) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter(Key.ID));
        String login = request.getParameter(Key.LOGIN);
        String role = request.getParameter(Key.ROLE);
        String password = request.getParameter(Key.PASSWORD);
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.containsKey("create")) {
            Optional<UserTo> user = userService.create(id, login, password, role);
            imageService.uploadImage(request, user.orElseThrow().getImage());
        } else if (parameterMap.containsKey("update")) {
            Optional<UserTo> user = userService.update(id, login, password, role);
            imageService.uploadImage(request, user.orElseThrow().getImage());
        } else if (parameterMap.containsKey("delete")) {
            userService.delete(id);
        } else throw new IllegalStateException("unknown command");
        return View.redirect(Key.USERS);
    }
}
