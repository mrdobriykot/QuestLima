package com.javarush.quest.marzhiievskyi.controller;

import com.javarush.quest.marzhiievskyi.entity.Quest;
import com.javarush.quest.marzhiievskyi.entity.User;
import com.javarush.quest.marzhiievskyi.service.GameSessionService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "QuestsServlet", value = "/quests")
public class QuestsServlet extends HttpServlet {
    GameSessionService gameSessionService = GameSessionService.GAME_SESSION_SERVICE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String targetForward = "WEB-INF/quests.jsp";

        if (user == null) {
            targetForward = "WEB-INF/login.jsp";
        }

        Collection<Quest> allQuests = gameSessionService.getAllQuests();
        request.setAttribute("quests", allQuests);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(targetForward);
        requestDispatcher.forward(request, response);
    }
}
