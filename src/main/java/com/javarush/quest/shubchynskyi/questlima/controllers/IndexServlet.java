package com.javarush.quest.shubchynskyi.questlima.controllers;

import com.javarush.quest.shubchynskyi.questlima.entity.user.Role;
import com.javarush.quest.shubchynskyi.questlima.util.Go;
import com.javarush.quest.shubchynskyi.questlima.util.Jsp;
import com.javarush.quest.shubchynskyi.questlima.util.Key;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "IndexServlet", value = Go.ROOT, loadOnStartup = 0)
public class IndexServlet extends HttpServlet {

    @Override
    //TODO перенести в слушатель
    public void init(ServletConfig config) throws ServletException {
        //TODO заполнить квесты и юзеров через метод в конфиге
        config.getServletContext().setAttribute(Key.ROLES, Role.values());
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsp.forward(req, resp, Key.INDEX);
    }
}
