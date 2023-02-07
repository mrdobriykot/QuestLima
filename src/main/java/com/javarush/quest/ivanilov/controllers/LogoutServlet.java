package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Logs;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import com.javarush.quest.ivanilov.utils.Navigator;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebServlet(name = "LogoutServlet", value = Targets.LOGOUT)
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Attributes.USER);
        HttpSession session = req.getSession();
        session.setAttribute(Attributes.IS_AUTHORIZED, false);
        session.setAttribute(Attributes.USER, null);
        session.setAttribute(Attributes.FIGHT, null);
        Navigator.dispatch(req, resp, Targets.INDEX);
        log.info(Logs.USER_LOGGED_OUT, user.getLogin());
    }
}
