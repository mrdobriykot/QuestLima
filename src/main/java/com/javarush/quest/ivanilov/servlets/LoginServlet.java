package com.javarush.quest.ivanilov.servlets;

import com.javarush.quest.ivanilov.controllers.AuthorizationService;
import com.javarush.quest.ivanilov.controllers.UserService;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Jsp;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "LoginServlet", value = Targets.LOGIN)
public class LoginServlet extends HttpServlet {
    public static final String NOT_AUTHORIZED = "Не получилось авторизовать пользователя.";
    private final AuthorizationService auth = AuthorizationService.AUTHORIZATION_SERVICE;
    private final UserService userService = UserService.USER_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isAuthorized = Boolean.getBoolean(req.getParameter(Attributes.IS_AUTHORIZED));
        if (isAuthorized) {
            Navigator.redirect(resp, Targets.MAIN);
        } else {
            Navigator.dispatch(req, resp, Jsp.LOGIN_JSP);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter(Attributes.LOGIN);
        String password = req.getParameter(Attributes.PASSWORD);
        User user = userService.find(login).orElse(null);
        boolean authSuccess = auth.authorize(user, password);

        if (authSuccess) {
            HttpSession session = req.getSession();
            session.setAttribute(Attributes.IS_AUTHORIZED, true);
            session.setAttribute(Attributes.USER, user);
            Navigator.redirect(resp, Targets.MAIN);
        } else {
            req.setAttribute(Attributes.MESSAGE, NOT_AUTHORIZED);
            Navigator.redirect(resp, Jsp.ERROR_MESSAGE_JSP);
        }
    }

}
