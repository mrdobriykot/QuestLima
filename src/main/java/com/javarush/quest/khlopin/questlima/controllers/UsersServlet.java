package com.javarush.quest.khlopin.questlima.controllers;

import com.javarush.quest.khlopin.questlima.entity.DB;
import com.javarush.quest.khlopin.questlima.entity.Role;
import com.javarush.quest.khlopin.questlima.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "UsersServlet", value = "/users")
public class UsersServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute("roles", Role.values());
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<User> allUsers = DB.userDataBase.getAll();
        request.setAttribute("users",allUsers);
//        System.out.println(allUsers);
//        for (User user : allUsers) {
//            System.out.println(user);
//        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/users.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
