package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import com.javarush.quest.ivanilov.entities.game.Event;
import com.javarush.quest.ivanilov.utils.Navigator;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "TaskDispatcherServlet", value = Targets.DISPATCH_TASK)
public class TaskDispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Event event = (Event) req.getSession().getAttribute(Attributes.CURR_EVENT);
        switch (event.getTask().getType()) {
            case QUESTION -> Navigator.redirect(resp, Targets.QUESTION);
            case FIGHT -> Navigator.redirect(resp, Targets.FIGHT);
        }
    }
}
