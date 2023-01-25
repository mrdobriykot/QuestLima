package com.javarush.quest.khlopin.questlima.controllers.questControllers;

import com.javarush.quest.khlopin.questlima.entity.game.Answer;
import com.javarush.quest.khlopin.questlima.entity.game.Quest;
import com.javarush.quest.khlopin.questlima.entity.game.Question;
import com.javarush.quest.khlopin.questlima.utills.DB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreateQuestsServlet", value = "/createQuests")
public class CreateQuestsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/topBarPages/createQuest.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
