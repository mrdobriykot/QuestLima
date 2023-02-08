package com.javarush.quest.khlopin.questlima.controllers.authControllers;

import com.javarush.quest.khlopin.questlima.utills.Constants;
import com.javarush.quest.khlopin.questlima.utills.DB;
import com.javarush.quest.khlopin.questlima.entity.user.User;
import com.javarush.quest.khlopin.questlima.services.CheckAdminService;
import com.javarush.quest.khlopin.questlima.utills.RedirectPaths;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(RedirectPaths.TO_LOGIN).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();

        if (parameterMap.containsKey(Constants.CREATE)) {
            Optional<User> user = DB.userDataBase.find(parameterMap.get(Constants.LOGIN)[0]);
            if (user.isPresent()) {
                User user1 = user.get();
                if (user1.getPassword().equals(parameterMap.get(Constants.PASSWORD)[0])) {
                    HttpSession session = request.getSession();
                    session.setAttribute(Constants.USER, user1);
                    CheckAdminService.checkAdmin(session);
                    request.getRequestDispatcher(RedirectPaths.TO_PROFILE).forward(request, response);
                    log.info("Пользователь" + user1 + "успешно авторизовался");
                } else {
                    request.getRequestDispatcher(RedirectPaths.NOT_SUCCESS).forward(request, response);
                    log.info("неудачная попытка авторизации");
                }
            } else {
                request.getRequestDispatcher(RedirectPaths.NOT_SUCCESS).forward(request, response);
                log.info("неудачная попытка авторизации");
            }
        }
    }
}
