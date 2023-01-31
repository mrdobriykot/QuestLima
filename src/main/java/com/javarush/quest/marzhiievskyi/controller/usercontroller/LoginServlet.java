package com.javarush.quest.marzhiievskyi.controller.usercontroller;

import com.javarush.quest.marzhiievskyi.entity.User;
import com.javarush.quest.marzhiievskyi.service.UserService;
import com.javarush.quest.marzhiievskyi.util.ServletConstants;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = ServletConstants.LOGIN_SERVLET, value = ServletConstants.LOGIN_PATH)
public class LoginServlet extends HttpServlet {

    UserService userService = UserService.USER_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ServletConstants.WEB_INF_LOGIN_JSP);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter(ServletConstants.LOGIN);
        String password = request.getParameter(ServletConstants.PASSWORD);

        Optional<User> optionalUser = userService.get(login, password);
        if (optionalUser.isPresent()) {
            HttpSession session = request.getSession();
            User user = optionalUser.get();
            session.setAttribute(ServletConstants.USER, user);
            response.sendRedirect(ServletConstants.PROFILE);
        } else {
            response.sendRedirect(ServletConstants.LOGIN);
        }

    }
}
