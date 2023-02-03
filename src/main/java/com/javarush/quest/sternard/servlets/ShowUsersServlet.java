package com.javarush.quest.sternard.servlets;

import com.javarush.quest.sternard.config.Settings;
import com.javarush.quest.sternard.entities.User;
import com.javarush.quest.sternard.service.UserService;
import com.javarush.quest.sternard.util.Attribute;
import com.javarush.quest.sternard.util.Go;
import com.javarush.quest.sternard.util.Page;
import com.javarush.quest.sternard.util.Paginator;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.javarush.quest.sternard.util.CommonCode.badParams;
import static com.javarush.quest.sternard.util.CommonCode.getCheckedParams;
import static com.javarush.quest.sternard.util.Parameter.PAGE_NUMBER;

@Slf4j
@WebServlet(name = "ShowUsersServlet", value = {Page.USERS_SERVLET})
public class ShowUsersServlet extends HttpServlet {
    private final UserService userService = UserService.INSTANCE;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Collection<User> users = userService.getAllEntities();

        Map<String, String> checkedParamsMap = getCheckedParams(request, List.of());
        if (checkedParamsMap != null) {
            String pageNum = checkedParamsMap.get(PAGE_NUMBER);
            int usersRecordsPerPagePaginator = Settings.get().getUsersRecordsPerPagePaginator();

            List<User> usersOnPage = Paginator.getEntitiesOnPage(request, users, pageNum, usersRecordsPerPagePaginator);

            request.setAttribute(Attribute.USERS, usersOnPage);
            Go.forward(request, response, Page.USERS_JSP);
        } else {
            badParams(request, response, request.getParameterMap(), Page.USERS_SERVLET);
        }
    }

}