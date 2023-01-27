package com.javarush.alimin.controller;

import com.javarush.alimin.config.Config;
import com.javarush.alimin.util.URLPatterns;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "IntroServlet", value = URLPatterns.HOME)
public class IntroServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        Config.setQuestContentFromJson();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(URLPatterns.INTRO);
        requestDispatcher.forward(request, response);

    }
}
