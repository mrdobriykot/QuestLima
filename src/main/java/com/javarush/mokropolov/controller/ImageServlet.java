package com.javarush.mokropolov.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;

import com.javarush.mokropolov.config.Winter;
import com.javarush.mokropolov.service.ImageService;
import com.javarush.mokropolov.util.Go;

@WebServlet(name = "ImageServlet", value = Go.IMAGES)
public class ImageServlet extends HttpServlet {

    private final ImageService imageService = Winter.getBean(ImageService.class);

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