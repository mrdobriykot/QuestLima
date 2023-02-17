package com.bogdanov.controller;

import com.bogdanov.util.Go;
import com.bogdanov.util.Jsp;
import com.bogdanov.util.Key;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;


@WebServlet(name = "LogoutServlet", value = Go.LOGOUT)
public class LogoutServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(Key.USER);
        Jsp.forward(request,response, Go.LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
