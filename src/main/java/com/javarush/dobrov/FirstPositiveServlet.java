package com.javarush.dobrov;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FirstPositiveServlet", value = "/FirstPositiveServlet")
public class FirstPositiveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath()+"/variants/first_positive.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}
