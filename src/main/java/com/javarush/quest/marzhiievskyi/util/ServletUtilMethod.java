package com.javarush.quest.marzhiievskyi.util;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ServletUtilMethod {

    public static void forward(HttpServletRequest request, HttpServletResponse response, String target) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(target);
        requestDispatcher.forward(request, response);
    }
}
