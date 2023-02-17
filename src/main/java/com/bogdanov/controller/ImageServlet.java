package com.bogdanov.controller;

import com.bogdanov.service.ImageService;
import com.bogdanov.util.Go;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(value = Go.IMAGES)
public class ImageServlet extends HttpServlet {

    private final ImageService imageService = ImageService.IMAGE_SERVICE;

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        String requestURI = request.getRequestURI();
        String target = request.getContextPath()+"/images/";
        String nameImage = requestURI.replace(target,"");
        Path path = imageService.getImagePath(nameImage);
        Files.copy(path,response.getOutputStream());
    }
}
