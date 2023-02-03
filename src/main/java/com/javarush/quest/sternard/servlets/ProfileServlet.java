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
import static com.javarush.quest.sternard.util.Parameter.ID;

@Slf4j
@WebServlet(name = "ProfileServlet", value = {Page.PROFILE_SERVLET})
public class ProfileServlet extends HttpServlet {
    private final UserService userService = UserService.INSTANCE;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<String, String[]> params = request.getParameterMap();
        Map<String, String> checkedParamsMap = getCheckedParams(request, List.of(ID));

        if (checkedParamsMap != null) {
            String id = checkedParamsMap.get(ID);

            Optional<User> optionalUser = userService.findUserById(Long.parseLong(id));

            if (optionalUser.isPresent()) {
                request.setAttribute(Attribute.USER, optionalUser.get());
                Go.forward(request, response, Page.PROFILE_JSP);
            } else {
                userSubstitutionParams(request, response, params);
            }
        } else {
            badParams(request, response, params, Page.USERS_SERVLET);
        }
    }

}