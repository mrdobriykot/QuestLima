package com.javarush.quest.marzhiievskyi.controller;

import com.javarush.quest.marzhiievskyi.entity.Quest;
import com.javarush.quest.marzhiievskyi.entity.User;
import com.javarush.quest.marzhiievskyi.service.GameSessionService;
import com.javarush.quest.marzhiievskyi.util.ServletConstants;
import com.javarush.quest.marzhiievskyi.util.ServletUtilMethod;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = ServletConstants.QUESTS_SERVLET, value = ServletConstants.QUESTS_PATH)
public class QuestsServlet extends HttpServlet {
    GameSessionService gameSessionService = GameSessionService.GAME_SESSION_SERVICE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ServletConstants.USER);

        String targetForward = ServletConstants.WEB_INF_QUESTS_JSP;

        if (user == null) {
            targetForward = ServletConstants.WEB_INF_LOGIN_JSP;
        }

        Collection<Quest> allQuests = gameSessionService.getAllQuests();
        request.setAttribute(ServletConstants.QUESTS, allQuests);

        ServletUtilMethod.forward(request, response, targetForward);
    }
}
