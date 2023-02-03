package com.javarush.quest.sternard.util;

import lombok.experimental.UtilityClass;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@UtilityClass
public class Go {
    public static void forward(HttpServletRequest request, HttpServletResponse response, String jspPage) throws ServletException, IOException {
        String rootPath = Page.FOLDER_JSP;
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(rootPath + jspPage);
        requestDispatcher.forward(request, response);
    }

    public static void redirect(HttpServletRequest request, HttpServletResponse response, String servletPage) throws IOException {
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + servletPage);
    }

}
