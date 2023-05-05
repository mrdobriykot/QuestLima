package com.javarush.khmelov.controller.cmd.impl;

import com.javarush.khmelov.controller.cmd.Command;
import com.javarush.khmelov.view.View;
import com.javarush.khmelov.service.QuestService;
import com.javarush.khmelov.view.Go;
import com.javarush.khmelov.controller.Key;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller(value = Go.HOME)
@AllArgsConstructor
public class IndexCommand implements Command {

//    private final Configurator configurator;

//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        configurator.fillEmptyRepository();
//        config.getServletContext().setAttribute(Key.ROLES, Role.values());
//        super.init(config);
//    }

    private final QuestService questService;

    @Override
    public View get(HttpServletRequest req) throws ServletException, IOException {
        req.setAttribute(Key.QUESTS, questService.getAll());
        return View.forward(Key.INDEX);
    }
}
