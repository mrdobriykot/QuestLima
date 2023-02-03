package com.bogdanov.controller;

import com.bogdanov.entity.User;
import com.bogdanov.service.ImageService;
import com.bogdanov.service.UserService;
import com.bogdanov.util.Go;
import com.bogdanov.util.Jsp;
import com.bogdanov.util.Key;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Map;


@WebServlet(name = "SignupServlet", value = Go.SIGNUP)
@MultipartConfig(fileSizeThreshold = 1<<20)
public class SignupServlet extends HttpServlet {


    UserService service= UserService.USER_SERVICE;
    ImageService imageService = ImageService.IMAGE_SERVICE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jsp.forward(request, response, Key.USER);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = Jsp.getUserCunstructor(request);
        if(parameterMap.containsKey("create")){
            service.create(user);
        } else if (parameterMap.containsKey("update")) {
            service.update(user);
        } else if (parameterMap.containsKey("delete")) {
            service.delete(user);
        }else throw new IllegalArgumentException("unknown command");

        imageService.uploadImage(request,user.getImage());

        Jsp.response(response,Go.USERS);
    }
}
