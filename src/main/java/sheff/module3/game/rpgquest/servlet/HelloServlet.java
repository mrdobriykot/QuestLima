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

@WebServlet("/start")
public class HelloServlet extends HttpServlet {
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

        req.setCharacterEncoding("utf-8");
        req.removeAttribute("tooltip");

        String continueBtn = req.getParameter("continueBtn");
        String removeBtn = req.getParameter("removeBtn");
        String userName = req.getParameter("username");

        session.setAttribute("users", req.getAttribute("users"));

        User user = userRepo.getUserByName(userName);

        if (removeBtn != null) {
            if (isUserExist(userName)) {
                userRepo.deleteUser(user);
                req.setAttribute("tooltip", String.format("<span class='text-success'>Пользователь '%s' удален</span>", userName));
            } else {
                req.setAttribute("tooltip", String.format("<span class='text-danger'>Пользователя '%s' не существует</span>", userName));
            }
            getServletContext().getRequestDispatcher("/").forward(req, resp);
            return;
        }

        if (continueBtn != null) {
            if (isUserExist(userName)) {
                req.setAttribute("locationIdForContinue", String.valueOf(user.getCurrentLocationId()));
                session.setAttribute("userName", userName);
                getServletContext().getRequestDispatcher("/action").forward(req, resp);
            } else {
                req.setAttribute("tooltip", String.format("<span class='text-danger'>Пользователя '%s' не существует</span>", userName));
                getServletContext().getRequestDispatcher("/").forward(req, resp);
            }
            return;
        }

        if (!userRepo.getUsers().isEmpty() && isUserExist(userName)) {
            req.setAttribute("tooltip", String.format("<span class='text-danger'>Пользователь '%s' уже существует</span>", userName));
            getServletContext().getRequestDispatcher("/").forward(req, resp);

        } else {
            session.removeAttribute("tooltip");
            session.setAttribute("userName", userName);
            userRepo.createUser(userName);
            getServletContext().getRequestDispatcher("/action").forward(req, resp);
        }
    }

    private boolean isUserExist(String userName) {
        return userRepo.getUsers().stream()
                .map(User::getName)
                .anyMatch(s -> s.equals(userName));
    }
}
