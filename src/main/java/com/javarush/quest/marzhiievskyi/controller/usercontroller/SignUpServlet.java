package com.javarush.quest.marzhiievskyi.controller.usercontroller;

import com.javarush.quest.marzhiievskyi.entity.User;
import com.javarush.quest.marzhiievskyi.service.UserService;
import com.javarush.quest.marzhiievskyi.util.ServletConstants;
import com.javarush.quest.marzhiievskyi.util.ServletUtilMethod;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = ServletConstants.SIGN_UP_SERVLET, value = ServletConstants.SIGNUP_PATH)
public class SignUpServlet extends HttpServlet {

    UserService userService = UserService.USER_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtilMethod.forward(request, response, ServletConstants.WEB_INF_SIGNUP_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User userToCreate = User.builder()
                .id(0L)
                .login(request.getParameter(ServletConstants.LOGIN))
                .password(request.getParameter(ServletConstants.PASSWORD))
                .build();

        if (!userService.checkUserExist(userToCreate)) {
            response.sendRedirect(ServletConstants.SIGNUP);
        } else {
            userService.create(userToCreate);
            HttpSession session = request.getSession();
            session.setAttribute(ServletConstants.USER, userToCreate);
            response.sendRedirect(ServletConstants.PROFILE);
        }
    }
}
