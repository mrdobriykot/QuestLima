package com.javarush.quest.ivanilov.servlets;

import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Jsp;
import com.javarush.quest.ivanilov.utils.constants.Messages;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.controllers.AuthorizationService;
import com.javarush.quest.ivanilov.controllers.UserService;
import com.javarush.quest.ivanilov.utils.Navigator;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "UsersServlet", value = Targets.USERS)
public class UsersServlet extends HttpServlet {
    private final AuthorizationService auth = AuthorizationService.AUTHORIZATION_SERVICE;
    private final UserService userService = UserService.USER_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Attributes.USER);

        if (auth.isAdmin(user)) {
            Collection<User> users = userService.getAll();
            req.setAttribute(Attributes.USERS, users);
            Navigator.dispatch(req, resp, Jsp.USERS_JSP);
        } else {
            req.setAttribute(Attributes.MESSAGE, new Messages().forbidden(user.getLogin()));
            Navigator.dispatch(req, resp, Jsp.ERROR_MESSAGE_JSP);
        }
    }
}
