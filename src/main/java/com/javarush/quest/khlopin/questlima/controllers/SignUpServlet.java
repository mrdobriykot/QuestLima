package com.javarush.quest.khlopin.questlima.controllers;

import com.javarush.quest.khlopin.questlima.entity.DB;
import com.javarush.quest.khlopin.questlima.entity.Role;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@WebServlet(name = "SignUpServlet", value = "/signup")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> stringEntry : parameterMap.entrySet()) {
            System.out.println(Arrays.toString(stringEntry.getValue()));
            System.out.println(stringEntry.getKey());
        }
        request.getRequestDispatcher("WEB-INF/signUp.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();

        DB.userDataBase.create(parameterMap.get("login")[0],
                parameterMap.get("password")[0],
                Role.USER);
        request.getRequestDispatcher("WEB-INF/complete.jsp").forward(request,response);
    }
}
