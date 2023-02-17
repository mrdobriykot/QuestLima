package com.bogdanov.controller;

import java.io.*;

import com.bogdanov.entity.Role;
import com.bogdanov.service.UserService;
import com.bogdanov.util.Go;
import com.bogdanov.util.Jsp;
import com.bogdanov.util.Key;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "userServlet", value = Go.USERS)
public class MenuServlet extends HttpServlet {



    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute(Key.ROLES, Role.values());
        super.init(config);
    }

    public final UserService service= UserService.USER_SERVICE;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(Key.USERS,service.getAll());
        Jsp.forward(request, response, Key.USERS);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doPost(req, resp);
    }

    public void destroy() {

    }
}