package com.javarush.quest.torkel.controller;

import com.javarush.quest.torkel.config.Config;
import com.javarush.quest.torkel.config.Winter;
import com.javarush.quest.torkel.entity.Role;
import com.javarush.quest.torkel.service.QuestService;
import com.javarush.quest.torkel.util.Go;
import com.javarush.quest.torkel.util.Jsp;
import com.javarush.quest.torkel.util.Key;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.javarush.quest.torkel.util.Key.INDEX;
import static com.javarush.quest.torkel.util.Key.QUESTS;

@WebServlet(name = "IndexServlet", value = Go.HOME, loadOnStartup = 0)
public class IndexServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        Config.fillEmptyRepository();
        config.getServletContext().setAttribute(Key.ROLES, Role.values());
        super.init(config);
    }

    private final QuestService questService = Winter.getBean(QuestService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(QUESTS, questService.getAll());
        Jsp.forward(req, resp, INDEX);
    }
}
