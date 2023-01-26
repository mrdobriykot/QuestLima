package com.javarush.quest.marzhiievskyi.controller.usercontroller;

import com.javarush.quest.marzhiievskyi.entity.GameSession;
import com.javarush.quest.marzhiievskyi.entity.User;
import com.javarush.quest.marzhiievskyi.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "MyProfile", value = "/profile")
public class MyProfileServlet extends HttpServlet {
    UserService userService = UserService.USER_SERVICE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String targetForward = "WEB-INF/profile.jsp";

        if (user == null) {
            targetForward = "WEB-INF/login.jsp";
        } else {
            Collection<GameSession> wins = userService.getWins(user.getId());
            Collection<GameSession> losses = userService.getLosses(user.getId());
            user.setWins(wins);
            user.setLosses(losses);
        }


        RequestDispatcher requestDispatcher = request.getRequestDispatcher(targetForward);
        requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        response.sendRedirect("user" + "?id=" + user.getId());
    }
}
