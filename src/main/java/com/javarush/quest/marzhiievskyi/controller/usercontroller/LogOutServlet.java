package com.javarush.quest.marzhiievskyi.controller.usercontroller;

import com.javarush.quest.marzhiievskyi.util.ServletConstants;
import com.javarush.quest.marzhiievskyi.util.ServletUtilMethod;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = ServletConstants.LOG_OUT_SERVLET, value = ServletConstants.LOGOUT)
public class LogOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        ServletUtilMethod.forward(request, response, ServletConstants.WEB_INF_LOGIN_JSP);
    }

}
