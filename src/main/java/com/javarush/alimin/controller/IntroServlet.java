package com.javarush.alimin.controller;

import com.javarush.alimin.config.Config;
import com.javarush.alimin.util.Forward;
import com.javarush.alimin.util.URLPatterns;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "IntroServlet", value = URLPatterns.HOME)
public class IntroServlet extends HttpServlet {


    private static final Logger log = LoggerFactory.getLogger(IntroServlet.class);

    @Override
    public void init() throws ServletException {
        Config.setQuestContentFromJson();
        log.debug("Quest content set");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Forward.forward(request, response, URLPatterns.INTRO);
    }
}
