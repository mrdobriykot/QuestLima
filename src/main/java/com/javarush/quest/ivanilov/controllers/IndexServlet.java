package com.javarush.quest.ivanilov.controllers;

import com.javarush.quest.ivanilov.utils.Navigator;
import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Jsp;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import java.io.IOException;

@Log4j2
@WebServlet(name = "IndexServlet", value = Targets.INDEX)
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isAuthorized = Boolean.getBoolean(req.getParameter(Attributes.IS_AUTHORIZED));
        if (isAuthorized) {
            Navigator.redirect(req, resp, Targets.MAIN);
        } else {
            Navigator.dispatch(req, resp, Jsp.INDEX_JSP);
        }
    }
}
