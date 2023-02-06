package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.entities.users.Role;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.UserService;
import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.constants.*;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebServlet(name = "SignupServlet", value = Targets.SIGNUP)
public class SignupServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = UserService.USER_SERVICE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Navigator.dispatch(req, resp, Jsp.SIGNUP_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(Attributes.LOGIN);
        String password = req.getParameter(Attributes.PASSWORD);
        User user = userService.createOrModifyUser(login, password, Role.USER, null);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute(Attributes.IS_AUTHORIZED, true);
            session.setAttribute(Attributes.USER, user);
            Navigator.redirect(req, resp, Targets.MAIN);
            log.info(Logs.USER_LOGGED_IN, user.getLogin());
        } else {
            Navigator.redirectError(req, resp, Messages.GENERIC_REASON);
        }
    }
}
