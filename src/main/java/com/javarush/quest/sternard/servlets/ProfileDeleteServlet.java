package com.javarush.quest.sternard.servlets;

import com.javarush.quest.sternard.entities.User;
import com.javarush.quest.sternard.service.UserService;
import com.javarush.quest.sternard.util.Go;
import com.javarush.quest.sternard.util.Page;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.javarush.quest.sternard.util.CommonCode.checkedParamsFromJson;
import static com.javarush.quest.sternard.util.Parameter.ID;
import static com.javarush.quest.sternard.util.message.LoggerMessages.*;

@Slf4j
@WebServlet(name = "ProfileDeleteServlet", value = Page.DELETE_USER_SERVLET)
public class ProfileDeleteServlet extends HttpServlet {
    private final UserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info(USER_PERMISSION_DENIED_TO_PAGE, Page.DELETE_USER_SERVLET);
        Go.redirect(req, resp, Page.HOME_SERVLET);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Map<String, String> checkedParamsMap = checkedParamsFromJson(req, List.of(ID));

        if (checkedParamsMap != null) {
            String id = checkedParamsMap.get(ID);
            Optional<User> optionalUser = userService.findUserById(Long.parseLong(id));
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                userService.delete(user.getId());
                log.info(USER_WAS_DELETED, user.getLogin());
            } else {
                log.warn(USER_SUBSTITUTION_PARAMETERS, parameterMap);
                resp.setStatus(404);
            }
        } else {
            log.warn(BAD_PARAMETERS, parameterMap);
            resp.setStatus(404);
        }
    }


}