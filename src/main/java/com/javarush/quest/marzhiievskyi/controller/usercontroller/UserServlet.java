package com.javarush.quest.marzhiievskyi.controller.usercontroller;

import com.javarush.quest.marzhiievskyi.entity.User;
import com.javarush.quest.marzhiievskyi.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {

    UserService userService = UserService.USER_SERVICE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameterId = request.getParameter("id");
        request.setAttribute("id", parameterId);
        if (Objects.nonNull(parameterId)) {
            Long id = Long.parseLong(parameterId);
            Optional<User> userOptional = userService.get(id);
            if (userOptional.isPresent()){
                User user = userOptional.get();
                request.setAttribute("user", user);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/user.jsp");
            requestDispatcher.forward(request, response);
        }
        response.sendRedirect("user");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = User.builder()
                .id(Long.valueOf(request.getParameter("id")))
                .login(request.getParameter("login"))
                .password(request.getParameter("password"))
                .build();
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.containsKey("create")) {
            userService.create(user);
        } else if (parameterMap.containsKey("update")) {
            userService.update(user);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        } else if (parameterMap.containsKey("delete")) {
            userService.delete(user);
            request.getSession().invalidate();
        } else throw new IllegalStateException("unknown command");
        response.sendRedirect("profile");
    }
}
