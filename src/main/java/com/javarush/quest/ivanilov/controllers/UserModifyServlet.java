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

@WebServlet(name = "UserModifyServlet", value = Targets.USER_MODIFY_ETC)
public class UserModifyServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = UserService.USER_SERVICE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = Long.parseLong(req.getParameter(Attributes.USER_ID));
        req.setAttribute(Attributes.USER_TO_BE_MODIFIED, userService.get(userId));
        req.setAttribute(Attributes.ROLES, Role.values());
        Navigator.dispatch(req, resp, Jsp.USER_MODIFY_JSP);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = Long.parseLong(req.getParameter(Attributes.USER_ID));
        String login = req.getParameter(Attributes.LOGIN);
        String password = req.getParameter(Attributes.PASSWORD);
        Role role = Role.valueOf(req.getParameter(Attributes.ROLE));
        User updatedUser = userService.createOrModifyUser(login, password, role, userService.get(userId));

        if (updatedUser != null) {
            long updatedUserId = updatedUser.getId();
            req.setAttribute(Attributes.MESSAGE, new Messages().userUpdated(userService.get(updatedUserId)));
            Navigator.dispatch(req, resp, Jsp.SUCCESS_MESSAGE_JSP);
        } else {
            Navigator.redirectError(req, resp, Messages.USER_NOT_MODIFIED);
        }

    }
}
