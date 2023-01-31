package com.javarush.quest.khlopin.questlima.controllers.usersControllers;

import com.javarush.quest.khlopin.questlima.services.CheckAdminService;
import com.javarush.quest.khlopin.questlima.utills.DB;
import com.javarush.quest.khlopin.questlima.entity.user.Role;
import com.javarush.quest.khlopin.questlima.entity.user.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "UsersServlet", value = "/users")
public class UsersServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (CheckAdminService.checkAdmin(request.getSession())) {
            Collection<User> allUsers = DB.userDataBase.getAll();
            request.setAttribute("users", allUsers);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/adminMenu/users.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
