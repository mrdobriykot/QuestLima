package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.AuthorizationService;
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
@WebServlet(name = "LoginServlet", value = Targets.LOGIN)
public class LoginServlet extends HttpServlet {
    private AuthorizationService auth;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        auth = AuthorizationService.AUTHORIZATION_SERVICE;
        userService = UserService.USER_SERVICE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Attributes.USER);
        boolean isAuthorized = Boolean.getBoolean(req.getParameter(Attributes.IS_AUTHORIZED));
        if (isAuthorized) {
            Navigator.redirect(req, resp, Targets.MAIN);
            log.info(Logs.USER_LOGGED_IN, user.getLogin());
        } else {
            Navigator.dispatch(req, resp, Jsp.LOGIN_JSP);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter(Attributes.LOGIN);
        String password = req.getParameter(Attributes.PASSWORD);
        User user = userService.find(login).orElse(null);
        boolean authSuccess = auth.authorize(user, password);

        if (authSuccess) {
            HttpSession session = req.getSession();
            session.setAttribute(Attributes.IS_AUTHORIZED, true);
            session.setAttribute(Attributes.USER, user);
            Navigator.redirect(req, resp, Targets.MAIN);
            log.info(Logs.USER_LOGGED_IN, user.getLogin());
        } else {
            Navigator.redirectError(req, resp, Messages.NOT_AUTHORIZED);
        }
    }

}
