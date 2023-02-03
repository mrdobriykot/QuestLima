package com.bogdanov.controller;

import com.bogdanov.entity.User;
import com.bogdanov.entity.Role;
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
import java.util.Objects;
import java.util.Optional;


@WebServlet(name = "UserServlet", value = Go.USER)
@MultipartConfig(fileSizeThreshold = 1<<20)
public class UserServlet extends HttpServlet {



    UserService service= UserService.USER_SERVICE;
    ImageService imageService = ImageService.IMAGE_SERVICE;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParametr = request.getParameter(Key.ID);
        if(Objects.nonNull(idParametr)) {
            Long id = null;

            try {
                id= Long.valueOf(idParametr);
                Optional<User> user = service.get(id);
                if(user.isPresent()){
                    request.setAttribute(Key.USER, user.get());

                }
                Jsp.forward(request, response,Key.USER);

            }catch (Exception e){
                request.setAttribute(Key.USERS,service.getAll());
                Jsp.forward(request, response,Key.USERS);
            }

        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = Jsp.getUserCunstructor(request);
        Map<String, String[]> parameterMap = request.getParameterMap();
        if(parameterMap.containsKey("create")){
            service.create(user);
        } else if (parameterMap.containsKey("update")) {
            service.update(user);
        } else if (parameterMap.containsKey("delete")) {
            service.delete(user);
        }else throw new IllegalArgumentException("unknown command");

        imageService.uploadImage(request,user.getId());

        Jsp.response(response,Go.USERS);


    }




}
