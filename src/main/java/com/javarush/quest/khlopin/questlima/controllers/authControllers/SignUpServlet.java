package com.javarush.quest.khlopin.questlima.controllers.authControllers;

import com.javarush.quest.khlopin.questlima.utills.DB;
import com.javarush.quest.khlopin.questlima.entity.user.Role;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "SignUpServlet", value = "/signup")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/auth/signUp.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            DB.userDataBase.create(parameterMap.get("login")[0],
                    parameterMap.get("password")[0],
                    Role.USER);
        } catch (RuntimeException e) {
            request.getRequestDispatcher("WEB-INF/auth/alreadyCreated.jsp").forward(request,response);
        }

        request.getRequestDispatcher("WEB-INF/adminMenu/complete.jsp").forward(request,response); // Move jsp file
    }
}
