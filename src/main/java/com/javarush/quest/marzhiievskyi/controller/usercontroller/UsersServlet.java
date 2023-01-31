package com.javarush.quest.marzhiievskyi.controller.usercontroller;

import com.javarush.quest.marzhiievskyi.entity.GameSession;
import com.javarush.quest.marzhiievskyi.entity.User;
import com.javarush.quest.marzhiievskyi.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "UsersServlet", value = "/users")
public class UsersServlet extends HttpServlet {
    private final UserService userService = UserService.USER_SERVICE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<User> users = userService.getAll();


        users.forEach(user -> {
            Collection<GameSession> wins = userService.getWins(user.getId());
            user.setWins(wins);
            Collection<GameSession> losses = userService.getLosses(user.getId());
            user.setLosses(losses);
        });

        request.setAttribute("users", users);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/users.jsp");
        requestDispatcher.forward(request, response);
    }
}
