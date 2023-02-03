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
import java.util.Objects;
import java.util.Optional;

import static com.javarush.quest.sternard.util.Attribute.ERROR_MESSAGE;
import static com.javarush.quest.sternard.util.CommonCode.getCheckedParams;
import static com.javarush.quest.sternard.util.Parameter.*;
import static com.javarush.quest.sternard.util.message.LoggerMessages.*;

@Slf4j
@WebServlet(name = "RegistrationServlet", value = {Page.REGISTRATION_SERVLET})
public class RegistrationServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute(Attribute.USER);

        if (Objects.isNull(userSession)) {
            Go.forward(request, response, Page.REGISTRATION_JSP);
        } else {
            log.info(USER_PERMISSION_DENIED_TO_PAGE, userSession.getLogin());
            Go.redirect(request, response, Page.HOME_SERVLET);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> requiredParams = List.of(LOGIN, PASSWORD_REG, ROLE, IMAGE);
        Map<String, String> checkedParamsMap = getCheckedParams(req, requiredParams);

        if (checkedParamsMap != null) {
            tryToRegisterNewUser(req, resp, checkedParamsMap);
        } else {
            log.warn(BAD_PARAMETERS, req.getParameterMap());
            req.setAttribute(ERROR_MESSAGE, ErrorMessages.USER_BAD_PARAMS);
            Go.forward(req, resp, Page.REGISTRATION_JSP);
        }
    }

    private void tryToRegisterNewUser(HttpServletRequest req, HttpServletResponse resp, Map<String, String> checkedParamsMap)
            throws IOException, ServletException {
        String login = checkedParamsMap.get(LOGIN);

        UserService userService = UserService.INSTANCE;
        Optional<User> optionalUser = userService.create(checkedParamsMap);

        if (optionalUser.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute(Attribute.USER, optionalUser.get());

            Go.redirect(req, resp, Page.HOME_SERVLET);
        } else {
            log.info(THE_USER_IS_EXIST_ON_REGISTER_PAGE, login, Page.REGISTRATION_SERVLET);
            req.setAttribute(ERROR_MESSAGE, ErrorMessages.USER_EXIST);
            Go.forward(req, resp, Page.REGISTRATION_JSP);
        }
    }
}