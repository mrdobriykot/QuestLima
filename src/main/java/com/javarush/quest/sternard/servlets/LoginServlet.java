package com.javarush.quest.sternard.servlets;

import com.javarush.quest.sternard.entities.User;
import com.javarush.quest.sternard.service.UserService;
import com.javarush.quest.sternard.util.Attribute;
import com.javarush.quest.sternard.util.Go;
import com.javarush.quest.sternard.util.Page;
import com.javarush.quest.sternard.util.message.ErrorMessages;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.javarush.quest.sternard.util.CommonCode.getCheckedParams;
import static com.javarush.quest.sternard.util.Parameter.LOGIN;
import static com.javarush.quest.sternard.util.Parameter.PASSWORD;
import static com.javarush.quest.sternard.util.message.LoggerMessages.*;

@Slf4j
@WebServlet(name = "LoginServlet", value = {Page.LOGIN_SERVLET})
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserService.INSTANCE;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute(Attribute.USER);

        if (userSession == null) {
            Go.forward(request, response, Page.LOGIN_JSP);
        } else {
            log.warn(USER_PERMISSION_DENIED_TO_PAGE, userSession.getLogin());
            Go.redirect(request, response, Page.HOME_SERVLET);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> requiredParams = List.of(LOGIN, PASSWORD);
        Map<String, String> checkedParamsMap = getCheckedParams(req, requiredParams);

        if (checkedParamsMap != null) {
            String login = checkedParamsMap.get(LOGIN);
            String password = checkedParamsMap.get(PASSWORD);

            Optional<User> optionalUser = userService.findUserByLoginPassword(login, password);

            if (optionalUser.isPresent()) {
                userIsAuthorized(req, resp, optionalUser);
            } else {
                userIsNotAuthorized(req, resp, login);
            }
        } else {
            badCheckedParams(req, resp, req.getParameterMap());
        }
    }

    private void badCheckedParams(HttpServletRequest req, HttpServletResponse resp, Map<String, String[]> params)
            throws ServletException, IOException {
        log.warn(BAD_PARAMETERS, params);
        req.setAttribute(Attribute.ERROR_MESSAGE, ErrorMessages.USER_BAD_PARAMS);
        Go.forward(req, resp, Page.LOGIN_JSP);
    }

    private void userIsNotAuthorized(HttpServletRequest req, HttpServletResponse resp, String login)
            throws ServletException, IOException {
        log.info(USER_NOT_FOUND_OR_INCORRECT_FORM_DATA, login);
        req.setAttribute(Attribute.ERROR_MESSAGE, ErrorMessages.USER_NOT_FOUND_OR_PASSWORD_INCORRECT);
        Go.forward(req, resp, Page.LOGIN_JSP);
    }

    @SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "OptionalGetWithoutIsPresent"})
    private void userIsAuthorized(HttpServletRequest req, HttpServletResponse resp, Optional<User> optionalUser)
            throws IOException {
        HttpSession session = req.getSession();
        User user = optionalUser.get();
        session.setAttribute(Attribute.USER, user);
        log.info(USER_IS_LOGGED_NOW, user.getLogin());
        Go.redirect(req, resp, Page.HOME_SERVLET);
    }

}