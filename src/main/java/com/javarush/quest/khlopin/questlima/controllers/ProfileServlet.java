package com.javarush.quest.khlopin.questlima.controllers;

import com.javarush.quest.khlopin.questlima.entity.User;
import com.javarush.quest.khlopin.questlima.services.CheckAdminService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CheckAdminService.checkAdmin(request.getSession());
        request.getRequestDispatcher("WEB-INF/profile.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
