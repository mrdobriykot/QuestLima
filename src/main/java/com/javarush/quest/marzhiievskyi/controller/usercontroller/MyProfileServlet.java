package com.javarush.quest.marzhiievskyi.controller.usercontroller;

import com.javarush.quest.marzhiievskyi.entity.GameSession;
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
import java.util.Collection;

@WebServlet(name = ServletConstants.MY_PROFILE_SERVLET, value = ServletConstants.PROFILE_PATH)
public class MyProfileServlet extends HttpServlet {
    UserService userService = UserService.USER_SERVICE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ServletConstants.USER);

        String targetForward = ServletConstants.WEB_INF_PROFILE_JSP;

        if (user == null) {
            targetForward = ServletConstants.WEB_INF_LOGIN_JSP;
        } else {
            Collection<GameSession> wins = userService.getWins(user.getId());
            Collection<GameSession> losses = userService.getLosses(user.getId());
            user.setWins(wins);
            user.setLosses(losses);
        }
        ServletUtilMethod.forward(request, response, targetForward);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ServletConstants.USER);
        String userIdPattern = "?id=";
        response.sendRedirect(ServletConstants.USER + userIdPattern + user.getId());
    }
}
