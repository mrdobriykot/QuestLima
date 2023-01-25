package com.javarush.quest.khlopin.questlima.controllers.usersControllers;

import com.javarush.quest.khlopin.questlima.entity.user.User;
import com.javarush.quest.khlopin.questlima.utills.DB;
import com.javarush.quest.khlopin.questlima.entity.user.Role;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "CreateUserServlet", value = "/createNewUser")
public class CreateUserServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute("roles", Role.values());
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/adminMenu/createNewUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        DB.userDataBase.create(parameterMap.get("login")[0],
                parameterMap.get("password")[0],
                Role.valueOf(parameterMap.get("role")[0]));

        request.getRequestDispatcher("WEB-INF/complete.jsp").forward(request,response);
    }
}
