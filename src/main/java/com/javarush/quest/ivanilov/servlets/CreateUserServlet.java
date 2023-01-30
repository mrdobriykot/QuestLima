package com.javarush.quest.ivanilov.servlets;

import com.javarush.quest.ivanilov.constants.Attributes;
import com.javarush.quest.ivanilov.constants.Messages;
import com.javarush.quest.ivanilov.constants.Targets;
import com.javarush.quest.ivanilov.entities.users.Roles;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.AuthorizationService;
import com.javarush.quest.ivanilov.services.UserService;
import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.RequestUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "CreateUserServlet", value = Targets.USER_CREATE)
public class CreateUserServlet extends HttpServlet {
    private final AuthorizationService auth = AuthorizationService.AUTHORIZATION_SERVICE;
    private final UserService userService = UserService.USER_SERVICE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        auth.authorizeAndProceed(req, resp, Targets.LOGIN_JSP);
        User user = (User) req.getSession().getAttribute(Attributes.USER);

        if (auth.isAdmin(user)) {
            req.setAttribute(Attributes.ROLES, Roles.values());
            Navigator.dispatch(req, resp, Targets.USER_CREATE_JSP);
        } else {
            req.setAttribute(Attributes.MESSAGE, new Messages().forbidden(user.getLogin()));
            Navigator.dispatch(req, resp, Targets.ERROR_MESSAGE_JSP);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        auth.authorizeAndProceed(req, resp, Targets.LOGIN_JSP);
        User user = (User) req.getSession().getAttribute(Attributes.USER);

        if (auth.isAdmin(user)) {
            Map<String, String> attributes = RequestUtils.getAttributes(req);
            String login = attributes.getOrDefault(Attributes.LOGIN, null);
            String password = attributes.getOrDefault(Attributes.PASSWORD, null);

            if (StringUtils.isBlank(login)) {
                req.setAttribute(Attributes.MESSAGE, Messages.NO_LOGIN);
                Navigator.dispatch(req, resp, Targets.ERROR_MESSAGE_JSP);
                return;
            } //TODO Рефакторинг, код повторяется с SignUp Servlet

            if (StringUtils.isBlank(password)) {
                req.setAttribute(Attributes.MESSAGE, Messages.NO_PASSWORD);
                Navigator.dispatch(req, resp, Targets.ERROR_MESSAGE_JSP);
                return;
            }

            Optional<User> optionalUser = userService.find(login);
            if (optionalUser.isPresent()) {
                req.setAttribute(Attributes.MESSAGE, new Messages().userAlreadyExists(optionalUser.get().getLogin()));
                Navigator.dispatch(req, resp, Targets.ERROR_MESSAGE_JSP);
                return;
            }

            User userToBeCreated = User.builder()
                    .login(login)
                    .password(password)
                    .role(Roles.valueOf(attributes.get(Attributes.ROLE)))
                    .build();

            User newUser = userService.create(userToBeCreated);
            req.setAttribute(Attributes.MESSAGE, new Messages().userCreated(newUser));
            Navigator.dispatch(req, resp, Targets.SUCCESS_MESSAGE_JSP);
        } else {
            req.setAttribute(Attributes.MESSAGE, new Messages().forbidden(user.getLogin()));
            Navigator.dispatch(req, resp, Targets.ERROR_MESSAGE_JSP);
        }
    }
}
