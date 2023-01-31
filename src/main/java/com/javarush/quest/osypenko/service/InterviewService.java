package com.javarush.quest.osypenko.service;

import com.javarush.quest.osypenko.costants.Constant;
import com.javarush.quest.osypenko.repository.Util;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class InterviewService {
    private final List<Object> questInterview = new Util().getQuestInterview();
    private int index;
    private int result;

    public void questionInterview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        while (index < questInterview.size()) {
            request.setAttribute("index", index + 1);
            request.setAttribute("size", questInterview.size());
            request.setAttribute("question", questInterview.get(index));

            RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.WEB_INF_INTERVIEW_INTERVIEW_JSP);
            dispatcher.forward(request, response);
        }

        resultInterview(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.WEB_INF_INTERVIEW_RESULT_JSP);
        dispatcher.forward(request, response);
    }

    private void resultInterview(HttpServletRequest request) {
        index = 0;
        questInterview.clear();
        new Util().getQuestInterview();

        int percentage = (result * 100) / questInterview.size();

        request.setAttribute("percentage", percentage);
        request.setAttribute("result", result);
        request.setAttribute("interviewSize", questInterview.size());

        result = 0;
    }


    public void buttonResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        String parameter = null;
        for (String str : parameterMap.keySet()) {
            parameter = str;
        }

        assert parameter != null;
        switch (parameter) {
            case Constant.BUTTON_NO -> {
                index++;
                questionInterview(request, response);
            }
            case Constant.BUTTON_YES -> {
                index++;
                result++;
                questionInterview(request, response);
            }
            case Constant.BUTTON_ANSWER -> {
                request.setAttribute("answer", questInterview.get(index));
                RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.WEB_INF_INTERVIEW_ANSWER_JSP);
                dispatcher.forward(request, response);
            }
        }
    }
}


