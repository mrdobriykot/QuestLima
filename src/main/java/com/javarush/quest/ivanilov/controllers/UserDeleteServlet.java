package com.javarush.quest.ivanilov.controllers;

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

@WebServlet(name = "UserDeleteServlet", value = Targets.USER_DELETE_ETC)
public class UserDeleteServlet extends HttpServlet {
    private final UserService userService = UserService.USER_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = Long.parseLong(req.getParameter(Attributes.USER_ID));
        User userToBeDeleted = userService.get(userId);
        req.setAttribute(Attributes.USER_TO_BE_MODIFIED, userToBeDeleted);
        Navigator.dispatch(req, resp, Jsp.USER_DELETE_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = Long.parseLong(req.getParameter(Attributes.USER_ID));
        User userToBeDeleted = userService.get(userId);
        userService.delete(userToBeDeleted);
        req.setAttribute(Attributes.MESSAGE, new Messages().userDeleted(userToBeDeleted));
        Navigator.dispatch(req, resp, Jsp.SUCCESS_MESSAGE_JSP);
    }

}
