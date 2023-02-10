package com.javarush.quest.khlopin.questlima.controllers.usersControllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "adminMenuServlet", value = "/adminMenu")
public class AdminMenuServlet extends HttpServlet {

    //TODO заменить на константы
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/adminMenu/adminMenu.jsp").forward(request, response);
    }
}
