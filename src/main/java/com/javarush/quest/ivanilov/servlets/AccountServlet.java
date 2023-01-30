package com.javarush.quest.ivanilov.servlets;

import com.javarush.quest.ivanilov.utils.constants.Attributes;
import com.javarush.quest.ivanilov.utils.constants.Jsp;
import com.javarush.quest.ivanilov.utils.constants.Targets;
import com.javarush.quest.ivanilov.entities.game.GameStatus;
import com.javarush.quest.ivanilov.entities.users.User;
import com.javarush.quest.ivanilov.controllers.GameService;
import com.javarush.quest.ivanilov.controllers.UserService;
import com.javarush.quest.ivanilov.utils.Navigator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AccountServlet", value = Targets.ACCOUNT)
public class AccountServlet extends HttpServlet {
    UserService userService = UserService.USER_SERVICE;
    GameService gameService = GameService.GAME_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Attributes.USER);
        List<Long> gamesPlayed = userService.get(user.getId()).getGamesPlayed();
        long gamesWon = gamesPlayed.stream().filter(o -> gameService.get(o).getStatus().equals(GameStatus.WON)).count();
        req.getSession().setAttribute(Attributes.NUMBER_OF_GAMES_PLAYED, gamesPlayed.size());
        req.getSession().setAttribute(Attributes.GAMES_WON, gamesWon);
        Navigator.dispatch(req, resp, Jsp.ACCOUNT_JSP);
    }
}
