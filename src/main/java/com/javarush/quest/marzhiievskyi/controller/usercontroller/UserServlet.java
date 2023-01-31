package com.javarush.quest.marzhiievskyi.controller.usercontroller;

import com.javarush.quest.marzhiievskyi.entity.User;
import com.javarush.quest.marzhiievskyi.service.UserService;
import com.javarush.quest.marzhiievskyi.util.ErrorMessage;
import com.javarush.quest.marzhiievskyi.util.ServletConstants;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@WebServlet(name = ServletConstants.USER_SERVLET, value = ServletConstants.USER_PATH)
public class UserServlet extends HttpServlet {

    UserService userService = UserService.USER_SERVICE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameterId = request.getParameter(ServletConstants.ID);
        request.setAttribute(ServletConstants.ID, parameterId);
        if (Objects.nonNull(parameterId)) {
            Long id = Long.parseLong(parameterId);
            Optional<User> userOptional = userService.get(id);
            if (userOptional.isPresent()){
                User user = userOptional.get();
                request.setAttribute(ServletConstants.USER, user);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ServletConstants.WEB_INF_USER_JSP);
            requestDispatcher.forward(request, response);
        }
        response.sendRedirect(ServletConstants.USER);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = User.builder()
                .id(Long.valueOf(request.getParameter(ServletConstants.ID)))
                .login(request.getParameter(ServletConstants.LOGIN))
                .password(request.getParameter(ServletConstants.PASSWORD))
                .build();
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.containsKey(ServletConstants.CREATE)) {
            userService.create(user);
        } else if (parameterMap.containsKey(ServletConstants.UPDATE)) {
            User updatedUser = userService.update(user);
            HttpSession session = request.getSession();
            session.setAttribute(ServletConstants.USER, updatedUser);
        } else if (parameterMap.containsKey(ServletConstants.DELETE)) {
            userService.delete(user);
            request.getSession().invalidate();
        } else throw new IllegalStateException(ErrorMessage.UNKNOWN_COMMAND);
        response.sendRedirect(ServletConstants.PROFILE);
    }
}
