package com.javarush.quest.uzienko.questlima;

import com.javarush.quest.uzienko.questlima.config.UrlConfig;
import com.javarush.quest.uzienko.questlima.config.Winter;
import com.javarush.quest.uzienko.questlima.service.InitService;
import com.javarush.quest.uzienko.questlima.service.QuestService;
import com.javarush.quest.uzienko.questlima.utils.Jsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", value = "/", loadOnStartup = 0)
public class HelloServlet extends HttpServlet {

    private final InitService initService = Winter.getBean(InitService.class);

    public void init() {
        initService.initQuestData();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("questList", Winter.getBean(QuestService.class).getAll());
        Jsp.include(request, response, UrlConfig.VIEW_FOR_HELLO_SERVLET);

    }

    public void destroy() {
    }
}