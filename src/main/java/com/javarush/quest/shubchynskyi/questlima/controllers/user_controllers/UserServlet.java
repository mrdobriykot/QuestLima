package com.javarush.quest.shubchynskyi.questlima.controllers.user_controllers;

import com.javarush.quest.shubchynskyi.questlima.entity.user.Role;
import com.javarush.quest.shubchynskyi.questlima.entity.user.User;
import com.javarush.quest.shubchynskyi.questlima.exception.AppException;
import com.javarush.quest.shubchynskyi.questlima.service.ImageService;
import com.javarush.quest.shubchynskyi.questlima.service.UserService;
import com.javarush.quest.shubchynskyi.questlima.util.Go;
import com.javarush.quest.shubchynskyi.questlima.util.Jsp;
import com.javarush.quest.shubchynskyi.questlima.util.Key;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@WebServlet(name = "UserServlet", value = Go.USER)
@MultipartConfig(fileSizeThreshold = 1 << 20)
public class UserServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute(Key.ROLES, Role.values());
        super.init(config);
    }

    private final UserService userService = UserService.USER_SERVICE;
    private final ImageService imageService = ImageService.IMAGE_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameterId = req.getParameter(Key.ID);
        req.setAttribute(Key.ID, parameterId);
        if (Objects.nonNull(parameterId)) {
            long id = Long.parseLong(parameterId);
            Optional<User> optionalUser = userService.get(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                req.setAttribute(Key.USER, user);
            }
            Jsp.forward(req, resp, Key.USER);
        }
        Jsp.redirect(resp, Key.USERS);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO refactor
        Map<String, String[]> parameterMap1 = req.getParameterMap();
        for (var var : parameterMap1.entrySet()) {
            System.out.println(var.getKey());
            System.out.println(Arrays.toString(var.getValue()));
            System.out.println();
        }

        User user = User.builder()
                .id(Long.valueOf(req.getParameter(Key.ID)))
                .login(req.getParameter(Key.LOGIN))
                .password(req.getParameter(Key.PASSWORD))
                .role(Role.valueOf(req.getParameter(Key.ROLE)))
                .build();

        Map<String, String[]> parameterMap = req.getParameterMap();
        if (parameterMap.containsKey("create")) {
            userService.create(user);
        } else if (parameterMap.containsKey("update")) {
            userService.update(user);
        } else if (parameterMap.containsKey("delete")) {
            userService.delete(user);
        } else throw new AppException("Unknown command");

        imageService.uploadImage(req, user.getImage());

        resp.sendRedirect(Key.USERS);
    }
}
