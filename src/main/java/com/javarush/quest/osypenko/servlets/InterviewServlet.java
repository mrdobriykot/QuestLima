package com.javarush.quest.osypenko.servlets;

import com.javarush.quest.osypenko.repository.DB;
import com.javarush.quest.osypenko.repository.Util;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "InterviewServlet", value = "/interview")
public class InterviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Все вопросы
        TreeMap<Long, DB> dbTreeMap = new Util().getMapEntity();

        //Случайные числа
        TreeSet<Integer> random = new TreeSet<>();
        //TODO поменять число задаваемых вопросов
        while (random.size() < 10) {
            random.add(ThreadLocalRandom.current().nextInt(0, dbTreeMap.size()));
        }

        //Получение списка id вопросов
        List<Long> idQuest = new ArrayList<>(dbTreeMap.keySet());

        List<Object> questInterview = new ArrayList<>();

        for (Integer integers : random) {
            Object aLong = idQuest.get(integers);
            questInterview.add(dbTreeMap.get(aLong));
        }

        Map<String, String[]> parameterMap = request.getParameterMap();
        String parameter = null;
        for (String str : parameterMap.keySet()) {
            parameter = str;
        }
        System.out.println(parameter);


        request.setAttribute("quest", questInterview);




        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/interview.jsp");
        dispatcher.forward(request, response);
    }
}
