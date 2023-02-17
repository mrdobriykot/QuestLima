package com.bogdanov.controller;

import com.bogdanov.entity.Role;
import com.bogdanov.entity.User;
import com.bogdanov.util.Go;
import com.bogdanov.util.Jsp;
import com.bogdanov.util.Key;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;




@WebServlet(name = "ProfileServlet", value = Go.PROFILE)
public class ProfileServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute(Key.ROLES, Role.values());
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jsp.forward(request,response, Go.PROFILE);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        User user = (User)session.getAttribute(Key.USER);
        Jsp.response(response,Go.USER+"?id="+user.getId());
    }
}
