package com.javarush.khmelov.controller;

import com.javarush.khmelov.config.Spring;
import com.javarush.khmelov.dto.UserTo;
import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.service.ImageService;
import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.util.Go;
import com.javarush.khmelov.util.Jsp;
import com.javarush.khmelov.util.Key;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(Go.SIGNUP)
@MultipartConfig(fileSizeThreshold = 1 << 20)
public class SignupServlet extends HttpServlet {

    private final UserService userService = Spring.getBean(UserService.class);
    private final ImageService imageService = Spring.getBean(ImageService.class);

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
        String login = request.getParameter(Key.LOGIN);
        String password = request.getParameter(Key.PASSWORD);

        Optional<UserTo> userTo = userService.create(login, password);
        imageService.uploadImage(request, userTo.orElseThrow().getImage());
        Jsp.redirect(response, Go.USERS);
    }
}
