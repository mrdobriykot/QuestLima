package com.javarush.quest.sternard.servlets;

import com.javarush.quest.sternard.util.Go;
import com.javarush.quest.sternard.util.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeServlet", value = {Page.HOME_SERVLET, Page.HOME_SERVLET_EMPTY})
public class HomeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Go.forward(request, response, Page.INDEX_JSP);
    }
}