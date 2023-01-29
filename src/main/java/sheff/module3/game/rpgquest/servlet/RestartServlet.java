package sheff.module3.game.rpgquest.servlet;

import sheff.module3.game.rpgquest.entity.User;
import sheff.module3.game.rpgquest.repository.UserRepo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/restart")
public class RestartServlet extends HttpServlet {
    UserRepo userRepo;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        userRepo = (UserRepo) servletContext.getAttribute("userRepo");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String userName = (String) session.getAttribute("userName");

        User user = userRepo.getUserByName(userName);
        if (user != null){
            userRepo.restartUser(user);
        }

        getServletContext().getRequestDispatcher("/action").forward(req, resp);
    }
}
