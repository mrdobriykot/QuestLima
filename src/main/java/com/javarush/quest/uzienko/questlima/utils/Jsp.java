package com.javarush.quest.uzienko.questlima.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class Jsp {
    private Jsp() {
    }

    @SuppressWarnings("unused")
    public static void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        response.sendRedirect(path);
    }

    public static void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        request.getRequestDispatcher(String.format("WEB-INF/views/%s.jsp", path))
                .forward(request, response);
    }

    public static void include(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher(String.format("WEB-INF/views/%s.jsp", path))
                .include(request, response);
    }
}
