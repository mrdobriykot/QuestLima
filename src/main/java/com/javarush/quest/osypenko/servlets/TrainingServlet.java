package com.javarush.quest.osypenko.servlets;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "TrainingServlet", value = "/training")
public class TrainingServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        System.out.println(name);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/training.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}