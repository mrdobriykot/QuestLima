package com.javarush.quest.sternard.servlets;

import com.javarush.quest.sternard.entities.User;
import com.javarush.quest.sternard.service.UserService;
import com.javarush.quest.sternard.util.Attribute;
import com.javarush.quest.sternard.util.Go;
import com.javarush.quest.sternard.util.Page;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.javarush.quest.sternard.util.CommonCode.*;
import static com.javarush.quest.sternard.util.Parameter.*;
import static com.javarush.quest.sternard.util.message.ErrorMessages.USER_BAD_PARAMS_OR_LOGIN_BUSY;
import static com.javarush.quest.sternard.util.message.LoggerMessages.*;

@Slf4j
@WebServlet(name = "ProfileEditServlet", value = Page.EDIT_USER_SERVLET)
public class ProfileEditServlet extends HttpServlet {
    private final UserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.warn(USER_PERMISSION_DENIED_TO_PAGE, Page.EDIT_USER_SERVLET);
        Go.redirect(req, resp, Page.HOME_SERVLET);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> params = req.getParameterMap();
        Map<String, String> checkedParamsMap = getCheckedParams(req, List.of(ID, LOGIN, PASSWORD, IMAGE));

        if (checkedParamsMap != null) {
            String id = checkedParamsMap.get(ID);
            Optional<User> optionalUser = userService.findUserById(Long.parseLong(id));
            if (optionalUser.isPresent()) {
                tryToUpdateUser(req, resp, checkedParamsMap, optionalUser);
            } else {
                userSubstitutionParams(req, resp, params);
            }
        } else {
            badParams(req, resp, params, Page.HOME_SERVLET);
        }
    }

    @SuppressWarnings({"OptionalGetWithoutIsPresent", "OptionalUsedAsFieldOrParameterType"})
    private void tryToUpdateUser(HttpServletRequest req, HttpServletResponse resp, Map<String, String> checkedParamsMap,
                                 Optional<User> optionalUser) throws ServletException, IOException {
        User user = optionalUser.get();
        boolean isUserUpdated = userService.update(user, checkedParamsMap);

        long userId = user.getId();
        String userLogin = user.getLogin();
        if (isUserUpdated) {
            log.info(USER_WAS_UPDATED, userLogin, userId);
            Go.redirect(req, resp, Page.PROFILE_SERVLET + "?" + ID + "=" + userId);
        } else {
            req.setAttribute(Attribute.ERROR_MESSAGE, USER_BAD_PARAMS_OR_LOGIN_BUSY);
            req.setAttribute(Attribute.USER, user);
            log.info(USER_WAS_NOT_UPDATED, userLogin, userId);
            Go.forward(req, resp, Page.PROFILE_JSP);
        }
    }

}