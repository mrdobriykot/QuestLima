package com.javarush.quest.osypenko.servlets;

import com.javarush.quest.osypenko.costants.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "IndexServlet", value = "")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.WEB_INF_INDEX_JSP);
        dispatcher.forward(request, response);
    }
}
