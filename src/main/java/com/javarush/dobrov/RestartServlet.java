package com.javarush.dobrov;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RestartServlet", value = "/RestartServlet")
public class RestartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}
