package com.javarush.dobrov;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SecondServlet", value = "/FirstNegativeServlet")
public class FirstNegativeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath()+"/variants/first_negative.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}
