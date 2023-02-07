package com.javarush.quest.khlopin.questlima.controllers.authControllers;

import com.javarush.quest.khlopin.questlima.utills.Constants;
import com.javarush.quest.khlopin.questlima.utills.DB;
import com.javarush.quest.khlopin.questlima.entity.user.Role;
import com.javarush.quest.khlopin.questlima.utills.RedirectPaths;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "SignUpServlet", value = "/signup")
public class SignUpServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(RedirectPaths.TO_SIGN_UP).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            DB.userDataBase.create(parameterMap.get(Constants.LOGIN)[0],
                    parameterMap.get(Constants.PASSWORD)[0],
                    Role.USER);
        } catch (RuntimeException e) {
            request.getRequestDispatcher(RedirectPaths.ALREADY_CREATED).forward(request,response);
        }

        request.getRequestDispatcher(RedirectPaths.COMPLETE).forward(request,response);
    }
}
