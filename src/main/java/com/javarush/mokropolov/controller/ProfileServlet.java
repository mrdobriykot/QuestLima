package com.javarush.mokropolov.controller;import com.javarush.mokropolov.service.UserService;import com.javarush.mokropolov.util.Go;import com.javarush.mokropolov.util.Jsp;import jakarta.servlet.ServletException;import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.HttpServlet;import jakarta.servlet.http.HttpServletRequest;import jakarta.servlet.http.HttpServletResponse;import java.io.IOException;@WebServlet(name = "ProfileServlet", value = Go.PROFILE)public class ProfileServlet extends HttpServlet {    @Override    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        Jsp.forward(request, response, Go.LOGIN);    }    @Override    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {    }}