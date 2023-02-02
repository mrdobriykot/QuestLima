package com.javarush.quest.osypenko.servlets.interview;

import com.javarush.quest.osypenko.service.InterviewService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import static com.javarush.quest.osypenko.costants.Constant.*;

@WebServlet(name = INTERVIEW_SERVLET, value = INTERVIEW)
public class InterviewServlet extends HttpServlet {
    InterviewService interviewService = new InterviewService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        interviewService.questionInterview(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        interviewService.buttonResponse(request, response);
    }
}
