package com.javarush.quest.khlopin.questlima.controllers;

import com.javarush.quest.khlopin.questlima.utills.RedirectPaths;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;

@WebServlet(name = "DonateServlet", value = "/donate")
public class DonateServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(DonateServlet.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(RedirectPaths.TO_DONATE).forward(request,response);
        log.trace(request.getSession().getAttribute("user") + "посетил страницу с донатом");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
