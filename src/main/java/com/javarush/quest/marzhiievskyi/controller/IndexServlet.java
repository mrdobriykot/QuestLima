package com.javarush.quest.marzhiievskyi.controller;

import com.javarush.quest.marzhiievskyi.config.Init;
import com.javarush.quest.marzhiievskyi.util.ServletConstants;
import com.javarush.quest.marzhiievskyi.util.ServletUtilMethod;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = ServletConstants.INDEX_SERVLET, value = ServletConstants.INDEX_VALUE, loadOnStartup = 0)
public class IndexServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        Init init = new Init();
        init.initDefaultUsers();
        init.initQuests();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtilMethod.forward(request, response, ServletConstants.WEB_INF_INDEX_JSP);
    }
}
