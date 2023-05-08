package com.javarush.khmelov.controller.cmd.impl;

import com.javarush.khmelov.controller.cmd.Command;
import com.javarush.khmelov.dto.UserTo;
import com.javarush.khmelov.view.Go;
import com.javarush.khmelov.view.View;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller(Go.PROFILE)
@AllArgsConstructor
public class ProfileCommand implements Command {

    @Override
    public View get(HttpServletRequest request) throws ServletException, IOException {
        return View.forward(Go.PROFILE);
    }

    @Override
    public View post(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserTo user = (UserTo) session.getAttribute("user");
        return View.redirect(Go.USER + "?id=" + user.getId());
    }
}
