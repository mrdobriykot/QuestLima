package com.bogdanov.controller;

import com.bogdanov.entity.User;
import com.bogdanov.service.ImageService;
import com.bogdanov.service.UserService;
import com.bogdanov.util.Go;
import com.bogdanov.util.Jsp;
import com.bogdanov.util.Key;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "LogServlet", value = Go.LOGIN)
@MultipartConfig(fileSizeThreshold = 1<<20)
public class LogServlet extends HttpServlet {


    UserService service= UserService.USER_SERVICE;
    ImageService imageService = ImageService.IMAGE_SERVICE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jsp.forward(request, response, "log");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(Key.LOGIN);
        String password = request.getParameter(Key.PASSWORD);
        Optional<User> user = service.get(login, password);
        if(user.isPresent()) {
            HttpSession session = request.getSession();
            User userActual = user.get();
            session.setAttribute(Key.USER, userActual);
            imageService.uploadImage(request, userActual.getId());
            Jsp.response(response,Go.PROFILE);
        }else {
            Jsp.response(response,Go.USERS);

        }

    }
}
