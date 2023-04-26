package com.javarush.khmelov.controller.cmd;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Command {
    default void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    default void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
