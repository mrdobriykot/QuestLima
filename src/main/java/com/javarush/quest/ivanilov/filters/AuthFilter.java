package com.javarush.quest.ivanilov.filters;

import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Logs;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.utils.Navigator;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebFilter(
        filterName = "AuthFilter",
        value = {
                Targets.PLAY,
                Targets.MAIN,
                Targets.CONSTRUCTOR,
                Targets.USER_DELETE_ETC,
                Targets.USER_MODIFY_ETC,
                Targets.ACCOUNT,
                Targets.USERS,
                Targets.QUESTION,
                Targets.FIGHT,
                Targets.SHOW_RESULT,
                Targets.NEW_GAME,
                Targets.USER_CREATE,
                Targets.QUESTS
        })
public class AuthFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Attributes.USER);
        if (user != null) {
            chain.doFilter(req, resp);
            log.info(Logs.USER_AUTHORIZED, user.getLogin(), user.getRole(), req.getRequestURI());
        } else {
            Navigator.redirect(req, resp, Targets.INDEX);
        }
    }
}
