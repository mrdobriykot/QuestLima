package com.javarush.mokropolov.controller;

import com.javarush.mokropolov.entity.Role;
import com.javarush.mokropolov.entity.User;
import com.javarush.mokropolov.service.ImageService;
import com.javarush.mokropolov.service.UserService;
import com.javarush.mokropolov.util.Go;
import com.javarush.mokropolov.util.Jsp;
import com.javarush.mokropolov.util.Key;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@WebServlet(name = "UserServlet", value = Go.USER)
@MultipartConfig(fileSizeThreshold = 1 << 20)
public class UserServlet extends HttpServlet {

    UserService userService = UserService.USER_SERVICE;
    ImageService imageService = ImageService.IMAGE_SERVICE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameterId = request.getParameter(Key.ID);

        HttpSession session = request.getSession();//
        session.setAttribute(Key.ID, parameterId);//

        request.setAttribute(Key.ID, parameterId);
        if (Objects.nonNull(parameterId)) {
            long id = Long.parseLong(parameterId);
            Optional<User> optionalUser = userService.get(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                request.setAttribute(Key.USER, user);
            }
            Jsp.forward(request, response, Go.USER);
        }

        Jsp.redirect(response, Key.USERS);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = User.builder()
                .id(Long.valueOf(request.getParameter(Key.ID)))
                .login(request.getParameter(Key.LOGIN))
                .password(request.getParameter(Key.PASSWORD))
                .role(Role.valueOf(request.getParameter(Key.ROLE)))
                .build();
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.containsKey("create")) {
            userService.create(user);
        } else if (parameterMap.containsKey("update")) {
            userService.update(user);
        } else if (parameterMap.containsKey("delete")) {
            userService.delete(user);
        } else throw new IllegalStateException("unknown command");
        imageService.uploadImage(request, user.getImage());
        Jsp.redirect(response, Key.USER);
    }
}

