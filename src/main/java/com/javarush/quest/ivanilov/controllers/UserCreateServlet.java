package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.entities.users.Roles;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.UserService;
import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Jsp;
import com.javarush.quest.ivanilov.utils.constants.Messages;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "UserCreateServlet", value = Targets.USER_CREATE)
public class UserCreateServlet extends HttpServlet {
    private final UserService userService = UserService.USER_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(Attributes.ROLES, Roles.values());
        Navigator.dispatch(req, resp, Jsp.USER_CREATE_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(Attributes.LOGIN);
        String password = req.getParameter(Attributes.PASSWORD);
        Roles role = Roles.valueOf(req.getParameter(Attributes.ROLE));
        User newUser = userService.createOrModifyUser(login, password, role, null);

        if (newUser == null) {
            req.setAttribute(Attributes.MESSAGE, Messages.USER_NOT_CREATED);
            Navigator.dispatch(req, resp, Jsp.ERROR_MESSAGE_JSP);
        } else {
            long newUserId = newUser.getId();
            req.setAttribute(Attributes.MESSAGE, new Messages().userCreated(userService.get(newUserId)));
            Navigator.dispatch(req, resp, Jsp.SUCCESS_MESSAGE_JSP);
        }

    }
}
