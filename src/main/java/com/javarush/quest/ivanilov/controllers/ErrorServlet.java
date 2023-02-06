package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Jsp;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebServlet(name = "ErrorServlet", value = Targets.ERROR)
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Throwable error = (Throwable) session.getAttribute(Attributes.ERROR);
        req.setAttribute(Attributes.MESSAGE, error.getMessage());
        session.removeAttribute(Attributes.ERROR);
        Navigator.dispatch(req, resp, Jsp.ERROR_MESSAGE_JSP);
        log.error(error.getMessage(), error);
    }
}
