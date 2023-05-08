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

    private final ImageService imageService= Context.getBean(ImageService.class);

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String requestURI = req.getRequestURI();
        String target = req.getContextPath() + "/images/";
        String nameImage = requestURI.replace(target, "");
        Path path = imageService.getImagePath(nameImage);
        Files.copy(path, resp.getOutputStream());
    }

}


