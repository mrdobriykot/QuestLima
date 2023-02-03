package com.bogdanov.controller;

import com.bogdanov.entity.Role;
import com.bogdanov.entity.User;
import com.bogdanov.service.QuestService;
import com.bogdanov.util.Go;
import com.bogdanov.util.Jsp;
import com.bogdanov.util.Key;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AllQuestsServlet", value = Go.GET_ALL_QUESTS)
public class AllQuestsServlet extends HttpServlet {



    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute(Key.ROLES, Role.values());
        super.init(config);
    }

    public final QuestService  service= QuestService.QUEST_SERVICE;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User attribute = (User)request.getSession().getAttribute(Key.USER);
        request.setAttribute(Key.QUESTS,service.getOfAutor(attribute.getId()));
        Jsp.forward(request, response, Go.GET_ALL_QUESTS);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doPost(req, resp);
    }

    public void destroy() {

    }
}