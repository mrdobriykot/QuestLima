package com.javarush.quest.ivanilov.servlets;

import com.javarush.quest.ivanilov.constants.Attributes;
import com.javarush.quest.ivanilov.constants.Messages;
import com.javarush.quest.ivanilov.constants.Targets;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.AuthorizationService;
import com.javarush.quest.ivanilov.services.UserService;
import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.RequestUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

@Slf4j
@WebServlet(name = "LoginServlet", value = Targets.LOGIN)
public class LoginServlet extends HttpServlet {
    private final AuthorizationService auth = AuthorizationService.AUTHORIZATION_SERVICE;
    private final UserService userService = UserService.USER_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        auth.authorizeAndProceed(req, resp, Targets.MAIN, Targets.LOGIN_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> attributes = RequestUtils.getAttributes(req);
        String login = attributes.getOrDefault(Attributes.LOGIN, "");
        String password = attributes.getOrDefault(Attributes.PASSWORD, "");
        User user = userService.find(login).orElse(null);
        boolean authSuccess = auth.authorize(user, password);
        if (authSuccess) {
            HttpSession session = req.getSession();
            session.setAttribute(Attributes.IS_AUTHORIZED, true);
            session.setAttribute(Attributes.USER, user);
            Navigator.redirect(resp, Targets.MAIN);
        } else {
            req.setAttribute(Attributes.MESSAGE, Messages.GENERIC_REASON);
            Navigator.redirect(resp, Targets.ERROR_MESSAGE_JSP);
        }
    }

}
