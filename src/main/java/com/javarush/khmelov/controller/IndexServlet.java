package com.javarush.khmelov.controller;

import com.javarush.khmelov.config.Configuration;
import com.javarush.khmelov.config.Spring;
import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.service.QuestService;
import com.javarush.khmelov.util.Go;
import com.javarush.khmelov.util.Jsp;
import com.javarush.khmelov.util.Key;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.javarush.khmelov.util.Key.INDEX;
import static com.javarush.khmelov.util.Key.QUESTS;

@WebServlet(value = Go.HOME, loadOnStartup = 0)
public class IndexServlet extends HttpServlet {

    private final Configuration configuration = Spring.getBean(Configuration.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        configuration.fillEmptyRepository();
        config.getServletContext().setAttribute(Key.ROLES, Role.values());
        super.init(config);
    }

    private final QuestService questService = Spring.getBean(QuestService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(QUESTS, questService.getAll());
        Jsp.forward(req, resp, INDEX);
    }
}
