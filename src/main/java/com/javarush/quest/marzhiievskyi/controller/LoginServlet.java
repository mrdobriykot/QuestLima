package com.javarush.quest.marzhiievskyi.controller;

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
import java.util.Optional;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    UserService userService = UserService.USER_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Optional<User> optionalUser = userService.get(login, password);
        if (optionalUser.isPresent()) {
            HttpSession session = request.getSession();
            User user = optionalUser.get();
            session.setAttribute("user", user);
            response.sendRedirect("user" + "?id=" + user.getId());
        } else {
            response.sendRedirect("login"); //TODO msg with err
        }

    }
}
