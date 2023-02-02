package com.javarush.quest.uzienko.questlima.filter;

import com.javarush.quest.uzienko.questlima.config.Winter;
import com.javarush.quest.uzienko.questlima.service.StatisticService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(servletNames = {"QuestMainPage"})
public class QuestMainPageFilter implements Filter {
    private final StatisticService statisticService = Winter.getBean(StatisticService.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        if (request.getMethod().equalsIgnoreCase("GET")) {
            statisticService.updateQuest(request.getSession());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
