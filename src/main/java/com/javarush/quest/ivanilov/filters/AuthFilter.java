package com.javarush.quest.ivanilov.filters;

import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.utils.Navigator;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
        User currUser = (User) req.getSession().getAttribute(Attributes.USER);
        if (currUser != null) {
            chain.doFilter(req, resp);
        } else {
            Navigator.redirect(req, resp, Targets.INDEX);
        }
    }
}
