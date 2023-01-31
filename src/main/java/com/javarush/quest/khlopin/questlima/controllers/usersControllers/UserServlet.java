package com.javarush.quest.khlopin.questlima.controllers.usersControllers;

import com.javarush.quest.khlopin.questlima.utills.DB;
import com.javarush.quest.khlopin.questlima.entity.user.Role;
import com.javarush.quest.khlopin.questlima.entity.user.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        User user = DB.userDataBase.get(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("WEB-INF/adminMenu/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        User currentUser = DB.userDataBase.get(Long.parseLong(parameterMap.get("id")[0]));

        if (request.getParameter("delete") != null) {
            DB.userDataBase.delete(currentUser.getId());
        } else if (request.getParameter("update") != null) {
            User updatedUser = new User(Long.parseLong(parameterMap.get("id")[0]),
                    parameterMap.get("login")[0],
                    parameterMap.get("password")[0],
                    Role.valueOf(parameterMap.get("role")[0]),
                    new ArrayList<>());
            DB.userDataBase.update(currentUser.getId(), updatedUser);
        }

        request.getRequestDispatcher("WEB-INF/adminMenu/complete.jsp").forward(request, response);
    }
}
