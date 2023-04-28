package com.javarush.khmelov.controller;

import com.javarush.khmelov.config.Spring;
import com.javarush.khmelov.dto.UserTo;
import com.javarush.khmelov.service.ImageService;
import com.javarush.khmelov.service.UserService;
import com.javarush.khmelov.util.Go;
import com.javarush.khmelov.util.Jsp;
import com.javarush.khmelov.util.Key;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@WebServlet(Go.USER)
@MultipartConfig(fileSizeThreshold = 1 << 20)
public class UserServlet extends HttpServlet {

    private final UserService userService = Spring.getBean(UserService.class);
    private final ImageService imageService = Spring.getBean(ImageService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameterId = request.getParameter(Key.ID);
        request.setAttribute(Key.ID, parameterId);
        if (Objects.nonNull(parameterId)) {
            long id = Long.parseLong(parameterId);
            Optional<UserTo> optionalUser = userService.get(id);
            if (optionalUser.isPresent()) {
                UserTo user = optionalUser.get();
                request.setAttribute(Key.USER, user);
            }
            Jsp.forward(request, response, Go.USER);
        }
        Jsp.redirect(response,Go.USERS);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter(Key.ID));
        String login = request.getParameter(Key.LOGIN);
        String role = request.getParameter(Key.ROLE);
        String password = request.getParameter(Key.PASSWORD);
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.containsKey("create")) {
            Optional<UserTo> user=userService.create(id,login,password,role);
            imageService.uploadImage(request, user.orElseThrow().getImage());
        } else if (parameterMap.containsKey("update")) {
            Optional<UserTo> user=userService.update(id,login,password,role);
            imageService.uploadImage(request, user.orElseThrow().getImage());
        } else if (parameterMap.containsKey("delete")) {
            userService.delete(id);
        } else throw new IllegalStateException("unknown command");
        response.sendRedirect(Key.USERS);
    }
}
