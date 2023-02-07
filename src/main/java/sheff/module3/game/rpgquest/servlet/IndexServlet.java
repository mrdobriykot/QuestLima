package sheff.module3.game.rpgquest.servlet;

import sheff.module3.game.rpgquest.repository.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRepo userRepo = (UserRepo) getServletContext().getAttribute("userRepo");
        if (userRepo != null) {
            req.setAttribute("users", userRepo.getUsers());
        }

        getServletContext().getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
    }
}
