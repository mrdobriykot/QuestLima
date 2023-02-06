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
        filterName = "AuthFilter",
        value = {Targets.USER_MODIFY_ETC})
public class UserModifyAuthFilter extends HttpFilter {

    AuthorizationService auth = AuthorizationService.AUTHORIZATION_SERVICE;

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Attributes.USER);
        long userId = Long.parseLong(req.getParameter(Attributes.USER_ID));

        if (auth.isAdmin(user) || user.getId() == userId) {
            log.info(Logs.USER_AUTHORIZED, user, user.getRole(), req.getRequestURI());
            chain.doFilter(req, resp);
        } else {
            req.setAttribute(Attributes.MESSAGE, new Messages().forbidden(user.getLogin()));
            Navigator.dispatch(req, resp, Jsp.ERROR_MESSAGE_JSP);
        }
    }
}
