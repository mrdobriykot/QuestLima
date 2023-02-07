package com.javarush.quest.ivanilov.filters;

import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.services.AuthorizationService;
import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.constants.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebFilter(
        filterName = "AdminFilter",
        value = {
                Targets.USER_CREATE,
                Targets.USER_DELETE_ETC,
                Targets.QUEST_DELETE_ETC,
                Targets.QUESTS,
                Targets.USERS
        })
public class AdminFilter extends HttpFilter {

    AuthorizationService auth = AuthorizationService.AUTHORIZATION_SERVICE;

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Attributes.USER);

        if (auth.isAdmin(user)) {
            chain.doFilter(req, resp);
            log.info(Logs.USER_AUTHORIZED, user.getLogin(), user.getRole(), req.getRequestURI());
        } else {
            req.setAttribute(Attributes.MESSAGE, new Messages().forbidden(user.getLogin()));
            Navigator.dispatch(req, resp, Jsp.ERROR_MESSAGE_JSP);
            log.info(Logs.USER_NOT_AUTHORIZED, user.getLogin(), user.getRole(), req.getRequestURI());
        }
    }
}
