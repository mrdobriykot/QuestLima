package com.javarush.quest.khlopin.questlima.controllers.usersControllers;

import com.javarush.quest.khlopin.questlima.services.CheckAdminService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {

    //TODO Изменить на константы
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CheckAdminService.checkAdmin(request.getSession());
        request.getRequestDispatcher("WEB-INF/profile.jsp").forward(request,response);
    }
}
