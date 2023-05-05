package com.javarush.khmelov.controller;

import com.javarush.khmelov.config.Context;
import com.javarush.khmelov.service.ImageService;
import com.javarush.khmelov.view.Go;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(Go.IMAGES)
public class ImageServlet extends HttpServlet {

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        ImageService imageService = Context.getBean(ImageService.class);
        String requestURI = req.getRequestURI();
        String target = req.getContextPath() + "/images/";
        String nameImage = requestURI.replace(target, "");
        Path path = imageService.getImagePath(nameImage);
        Files.copy(path, resp.getOutputStream());
    }


//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ImageService imageService = Context.getBean(ImageService.class);
//        resp.getWriter().println("ok");
//        resp.getWriter().flush();
//    }
}


