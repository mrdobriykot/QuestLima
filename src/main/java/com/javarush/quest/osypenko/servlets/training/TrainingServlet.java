package com.javarush.quest.osypenko.servlets.training;

import java.io.*;

import com.javarush.quest.osypenko.costants.Constant;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "TrainingServlet", value = "/training")
public class TrainingServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.WEB_INF_TRAINING_TRAINING_JSP);
        dispatcher.forward(request, response);
    }
}