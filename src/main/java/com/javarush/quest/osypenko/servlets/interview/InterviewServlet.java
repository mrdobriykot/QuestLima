package com.javarush.quest.osypenko.servlets.interview;

import com.javarush.quest.osypenko.repository.Util;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "InterviewServlet", value = "/interview")
public class InterviewServlet extends HttpServlet {
    private final List<Object> questInterview = new Util().getQuestInterview();
    List<Boolean> result = new ArrayList<>();
    int index = 0;
    boolean flag;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        while (index < questInterview.size()) {
            request.setAttribute("index", index + 1);
            request.setAttribute("size", questInterview.size());
            request.setAttribute("question", questInterview.get(index));

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/interview/interview.jsp");
            dispatcher.forward(request, response);
        }
        //TODO result
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/interview/result.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        String parameter = null;
        for (String str : parameterMap.keySet()) {
            parameter = str;
        }

        assert parameter != null;
        switch (parameter) {
            case "NO" -> {
                flag = false;
                index++;
                doGet(request, response);
            }
            case "YES" -> {
                flag = true;
                index++;
                doGet(request, response);
            }
            case "ANSWER" -> {
                request.setAttribute("answer", questInterview.get(index));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/interview/answer.jsp");
                dispatcher.forward(request, response);
            }
        }
        result.add(flag);
    }
}
