package com.javarush.quest.osypenko.service;

import com.javarush.quest.osypenko.repository.Util;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.javarush.quest.osypenko.costants.Constant.*;

public class InterviewService {
    public static final Logger log = LogManager.getLogger(InterviewService.class);
    private final List<Object> questInterview = new Util().getQuestInterview();
    private int index;
    private int result;

    public void questionInterview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        while (index < questInterview.size()) {
            request.setAttribute(INDEX, index + 1);
            request.setAttribute(SIZE, questInterview.size());
            request.setAttribute(QUESTION, questInterview.get(index));

            RequestDispatcher dispatcher = request.getRequestDispatcher(WEB_INF_INTERVIEW_INTERVIEW_JSP);
            dispatcher.forward(request, response);
            log.debug(INTERVIEW_QUESTION, index + 1);
        }
        log.debug(EXIT_INTERVIEW);
        resultInterview(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher(WEB_INF_INTERVIEW_RESULT_JSP);
        dispatcher.forward(request, response);
    }

    private void resultInterview(HttpServletRequest request) {
        index = 0;
        questInterview.clear();
        log.debug(CLEAR_LIST_IN_INTERVIEW);
        new Util().getQuestInterview();
        log.debug(CREATE_NEW_LIST_QUESTION);

        int percentage = (result * 100) / questInterview.size();

        request.setAttribute(PERCENTAGE, percentage);
        request.setAttribute(RESULT, result);
        request.setAttribute(INTERVIEW_SIZE, questInterview.size());

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
            case BUTTON_NO -> {
                index++;
                log.debug(PUSH_BUTTON_NO);
                questionInterview(request, response);
            }
            case BUTTON_YES -> {
                index++;
                result++;
                log.debug(PUSH_BUTTON_YES);
                questionInterview(request, response);
            }
            case BUTTON_ANSWER -> {
                log.debug(PUSH_BUTTON_ANSWER);
                request.setAttribute(ANSWER, questInterview.get(index));
                RequestDispatcher dispatcher = request.getRequestDispatcher(WEB_INF_INTERVIEW_ANSWER_JSP);
                dispatcher.forward(request, response);
            }
        }
    }
}


