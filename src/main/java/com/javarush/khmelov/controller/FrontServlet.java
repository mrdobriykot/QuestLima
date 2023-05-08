package com.javarush.khmelov.controller;

import com.javarush.khmelov.config.Configurator;
import com.javarush.khmelov.config.Context;
import com.javarush.khmelov.controller.cmd.Command;
import com.javarush.khmelov.controller.cmd.CommandResolver;
import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.view.View;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.javarush.khmelov.view.Go.*;


@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(urlPatterns = {
        HOME,
        SIGNUP, LOGIN, LOGOUT, PROFILE, USER, USERS,
        CREATE, QUEST, STAT, GAME,
}, loadOnStartup = 0)
public class FrontServlet extends HttpServlet {

    public final Configurator configurator = Context.getBean(Configurator.class);
    private final CommandResolver commandResolver = Context.getBean(CommandResolver.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        configurator.fillEmptyRepository();
        config.getServletContext().setAttribute(Key.ROLES, Role.values());
        super.init(config);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = commandResolver.resolve(req.getRequestURI());
        View view = command.get(req);
        view.goToView(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = commandResolver.resolve(req.getRequestURI());
        View view = command.post(req);
        view.goToView(req, resp);
    }

}
