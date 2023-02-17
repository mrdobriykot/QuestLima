package com.javarush.quest.sternard.servlets;

import com.javarush.quest.sternard.service.StatisticService;
import com.javarush.quest.sternard.util.Go;
import com.javarush.quest.sternard.util.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StatisticServlet", value = Page.STATISTIC_SERVLET)
public class StatisticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StatisticService statisticService = StatisticService.INSTANCE;
        statisticService.getStatistic(req);
        Go.forward(req, resp, Page.STATISTIC_JSP);
    }
}
