package sheff.module3.game.rpgquest.servlet;

import sheff.module3.game.rpgquest.entity.Answer;
import sheff.module3.game.rpgquest.entity.Location;
import sheff.module3.game.rpgquest.entity.Question;
import sheff.module3.game.rpgquest.entity.User;
import sheff.module3.game.rpgquest.repository.AnswerRepo;
import sheff.module3.game.rpgquest.repository.PersonRepo;
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
import java.util.List;

@WebServlet("/person")
public class DialogServlet extends HttpServlet {
    UserRepo userRepo;
    PersonRepo personRepo;
    AnswerRepo answerRepo;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        userRepo = (UserRepo) servletContext.getAttribute("userRepo");
        personRepo = (PersonRepo) servletContext.getAttribute("personRepo");
        answerRepo = (AnswerRepo) servletContext.getAttribute("answerRepo");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int personId;
        String answerIdParam = req.getParameter("answerId");
        String personIdParam = req.getParameter("personId");

        if (personIdParam != null) {
            personId = Integer.parseInt(personIdParam);
            session.setAttribute("personId", personId);
        } else {
            personId = 0;
        }

        Question question = personRepo.getById(personId).getStartQuestion();

        if (answerIdParam != null) {
            question = answerRepo.getById(Integer.parseInt(answerIdParam)).getNext();
        }

        if (isDialogSuccess(question)) {
            String userName = (String) session.getAttribute("userName");
            User user = userRepo.getUserByName(userName);
            Location blockedLocation = personRepo.getById(personId).getKey().getLocation();
            user.getLocationRepo().getLocationById(blockedLocation.getId()).setBlock(false);
        }

        List<Answer> answers = answerRepo.getAnswersByQuestionId(question.getId());
        req.setAttribute("question", question);
        req.setAttribute("answers", answers);

        getServletContext().getRequestDispatcher("/jsp/action.jsp").forward(req, resp);
    }

    private boolean isDialogSuccess(Question question) {
        return question.getType() == Question.Type.SUCCESS;
    }
}
