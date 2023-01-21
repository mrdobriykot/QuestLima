package com.javarush.mokropolov.controller;

import com.javarush.mokropolov.entity.Role;
import com.javarush.mokropolov.entity.User;
import com.javarush.mokropolov.service.ImageService;
import com.javarush.mokropolov.service.UserService;
import com.javarush.mokropolov.util.Go;
import com.javarush.mokropolov.util.Jsp;
import com.javarush.mokropolov.util.Key;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SignupServlet", value = Go.SIGNUP)
@MultipartConfig(fileSizeThreshold = 1 << 20)
public class SignupServlet extends HttpServlet {

    UserService userService = UserService.USER_SERVICE;
    ImageService imageService = ImageService.IMAGE_SERVICE;
    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute(Key.ROLES, Role.values());
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jsp.forward(request,response, Go.SIGNUP);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = User.builder()
                .id(0L)
                .login(request.getParameter(Key.LOGIN))
                .password(request.getParameter(Key.PASSWORD))
                .role(Role.valueOf(request.getParameter(Key.ROLE)))
                .build();
        userService.create(user);
        imageService.uploadImage(request, user.getImage());
        Jsp.redirect(response, Key.USERS);
    }
}

