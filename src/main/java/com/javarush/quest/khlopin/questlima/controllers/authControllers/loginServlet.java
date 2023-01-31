package com.javarush.quest.khlopin.questlima.controllers.authControllers;

import com.javarush.quest.khlopin.questlima.utills.DB;
import com.javarush.quest.khlopin.questlima.entity.user.User;
import com.javarush.quest.khlopin.questlima.services.CheckAdminService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "loginServlet", value = "/login")
public class loginServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(loginServlet.class);

    boolean success;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/auth/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();

        if (parameterMap.containsKey("create")) {
            Optional<User> user = DB.userDataBase.find(parameterMap.get("login")[0]);
            if (user.isPresent()) {
                User user1 = user.get();
                if (user1.getPassword().equals(parameterMap.get("password")[0])) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user1);
                    CheckAdminService.checkAdmin(session);
                    request.getRequestDispatcher("WEB-INF/profile.jsp").forward(request, response);
                    log.info("Пользователь" + user1 + "успешно авторизовался");
                    request.setAttribute("success",true);
                }
            } else {
                request.getRequestDispatcher("WEB-INF/auth/noSuccess.jsp").forward(request, response);
                request.setAttribute("success",false);
                log.info("неудачная попытка авторизации");
            }
        }
    }
}
