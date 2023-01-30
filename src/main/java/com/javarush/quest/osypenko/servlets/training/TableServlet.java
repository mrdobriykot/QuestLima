package com.javarush.quest.osypenko.servlets.training;

import com.javarush.quest.osypenko.service.TrainingService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "TableServlet", value = "/table")
public class TableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TrainingService.extracted(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/training/table.jsp");
        dispatcher.forward(request, response);
    }
}
