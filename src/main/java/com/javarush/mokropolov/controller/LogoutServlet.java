package com.javarush.mokropolov.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import com.javarush.mokropolov.util.Go;
import com.javarush.mokropolov.util.Jsp; 

@WebServlet(name = "LogoutServlet", value = Go.LOGOUT)
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        Jsp.forward(request, response, Go.LOGIN);
    }
}
