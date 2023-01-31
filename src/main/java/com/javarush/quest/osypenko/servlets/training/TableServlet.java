package com.javarush.quest.osypenko.servlets.training;

import com.javarush.quest.osypenko.costants.Constant;
import com.javarush.quest.osypenko.service.TrainingService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "TableServlet", value = "/table")
public class TableServlet extends HttpServlet {
    TrainingService trainingService = new TrainingService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        trainingService.trainingTable(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.WEB_INF_TRAINING_TABLE_JSP);
        dispatcher.forward(request, response);
    }
}
