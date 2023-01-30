package com.javarush.quest.ivanilov.servlets;

import com.javarush.quest.ivanilov.constants.Attributes;
import com.javarush.quest.ivanilov.constants.Messages;
import com.javarush.quest.ivanilov.constants.Targets;
import com.javarush.quest.ivanilov.entities.users.Operation;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.AuthorizationService;
import com.javarush.quest.ivanilov.services.UserService;
import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.RequestUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "SignupServlet", value = Targets.SIGNUP)
public class SignupServlet extends HttpServlet {
    private final AuthorizationService auth = AuthorizationService.AUTHORIZATION_SERVICE;
    private final UserService userService = UserService.USER_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        auth.authorizeAndProceed(req, resp, Targets.MAIN, Targets.SIGNUP_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> attributes = RequestUtils.getAttributes(req);
        User user = userService.createOrModifyUser(req, resp, attributes, Operation.CREATE);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute(Attributes.IS_AUTHORIZED, true);
            session.setAttribute(Attributes.USER, user);
            Navigator.redirect(resp, Targets.MAIN);
        } else {
            req.setAttribute(Attributes.MESSAGE, Messages.GENERIC_REASON);
            Navigator.dispatch(req, resp, Targets.ERROR_MESSAGE_JSP);
        }
    }
}
