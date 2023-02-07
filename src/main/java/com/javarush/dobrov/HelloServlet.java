package com.javarush.dobrov;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HelloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private static final int GAMES_COUNT = 1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object gamesCount = getServletContext().getAttribute("gamesCount");
        int count = (int) gamesCount + 1;
        getServletContext().setAttribute("gamesCount",count);
        response.sendRedirect("/FirstServlet");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        getStats(request, response);

    }

    private static void getStats(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("userName");
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("userName", username);
        servletContext.setAttribute("gamesCount", GAMES_COUNT);
        response.sendRedirect("/FirstServlet");

    }
}
