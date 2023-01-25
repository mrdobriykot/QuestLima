package com.javarush.quest.khlopin.questlima.controllers.questControllers;

import com.javarush.quest.khlopin.questlima.utills.DB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "QuestListServlet", value = "/questList")
public class QuestListServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute("quests", DB.questDataBase.getAll());
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/topBarPages/questsList.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
