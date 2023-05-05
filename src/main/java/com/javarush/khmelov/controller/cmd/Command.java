package com.javarush.khmelov.controller.cmd;

import com.javarush.khmelov.view.View;
import com.javarush.khmelov.view.Go;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface Command {

    default View get(HttpServletRequest request) throws ServletException, IOException {
        return View.forward(Go.HOME);
    }

    default View post(HttpServletRequest request) throws ServletException, IOException {
        return View.redirect(Go.HOME);
    }
}
