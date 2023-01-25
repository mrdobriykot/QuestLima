package com.javarush.quest.khlopin.questlima.services;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class QuestRedirect {

    public void redirectOperation(HttpServletRequest request, HttpServletResponse response, String losePath, String rightPath) throws ServletException, IOException {
        String answer = request.getParameter("answer");

        if (answer == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/errorCases/errorCase.jsp");
            requestDispatcher.forward(request, response);
        } else if (answer.equals("false")) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(losePath);
            requestDispatcher.forward(request, response);


        } else if (answer.equals("true")) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(rightPath);
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/errorCases/errorCase.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
