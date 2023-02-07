package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.entities.users.Role;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.UserService;
import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Jsp;
import com.javarush.quest.ivanilov.utils.constants.Messages;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "UserCreateServlet", value = Targets.USER_CREATE)
public class UserCreateServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = UserService.USER_SERVICE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(Attributes.ROLES, Role.values());
        Navigator.dispatch(req, resp, Jsp.USER_CREATE_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(Attributes.LOGIN);
        String password = req.getParameter(Attributes.PASSWORD);
        Role role = Role.valueOf(req.getParameter(Attributes.ROLE));
        User newUser = userService.createOrModifyUser(login, password, role, null);

        if (newUser == null) {
            Navigator.redirectError(req, resp, Messages.USER_NOT_CREATED);
        } else {
            long newUserId = newUser.getId();
            req.setAttribute(Attributes.MESSAGE, new Messages().userCreated(userService.get(newUserId)));
            Navigator.dispatch(req, resp, Jsp.SUCCESS_MESSAGE_JSP);
        }

    }
}
