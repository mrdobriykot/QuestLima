package com.javarush.quest.khlopin.questlima.controllers.usersControllers;

import com.javarush.quest.khlopin.questlima.entity.user.User;
import com.javarush.quest.khlopin.questlima.utills.DB;
import com.javarush.quest.khlopin.questlima.utills.RedirectPaths;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;


@WebServlet(name = "ChangeUsersParamServlet", value = "/userChangeProfile")
public class ChangeUsersParamServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ChangeUsersParamServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/userChangeProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = (User) request.getSession().getAttribute("user");
        String oldLogin = user.getUserName();
        Optional<User> optionalUser = DB.userDataBase.find(oldLogin);
        if (optionalUser.isPresent()) {

            //Смена логина
            changeLogin(parameterMap, oldLogin, optionalUser.get());
            //Смена пароля
            changePassword(parameterMap, optionalUser.get());
        }

        //TODO сделать, чтобы имя не могло совпадать с другими именами в БД

        request.getRequestDispatcher(RedirectPaths.TO_PROFILE).forward(request, response);
    }

    //TODO перенести в UserRepository
    private static void changePassword(Map<String, String[]> parameterMap, User userFromDB) {
        if (parameterMap.containsKey("password")) {
            String newPassword = parameterMap.get("password")[0];
            if (!newPassword.isEmpty()) {
                userFromDB.setPassword(newPassword);
                log.info(userFromDB + " изменил свой пароль");
            }
        }
    }
    //TODO перенести в UserRepository
    private static void changeLogin(Map<String, String[]> parameterMap, String oldLogin, User userFromDB) {
        if (parameterMap.containsKey("login")) {
            String newLogin = parameterMap.get("login")[0];
            if (!newLogin.isEmpty()) {
                userFromDB.setUserName(newLogin);
                log.info(oldLogin + " изменил свой логин на " + newLogin);
            }
        }
    }
}
