package sheff.module3.game.rpgquest.servlet;

import sheff.module3.game.rpgquest.entity.User;
import sheff.module3.game.rpgquest.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HelloServletTest {

    @Mock
    private UserRepo userRepo;
    @Mock
    private HttpSession session;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private User user;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext context;
    @Mock
    private RequestDispatcher dispatcher;
    @Spy
    private HelloServlet servlet;

    @BeforeEach
    void setup() throws ServletException {
        when(servletConfig.getServletContext()).thenReturn(context);
        when(context.getAttribute("userRepo")).thenReturn(userRepo);
//        when(context.getAttribute("personRepo")).thenReturn(personRepo);
        servlet.init(servletConfig);
    }

    @Test
    void doPost() throws ServletException, IOException {
        String continueBtn = "continueBtn";
        String removeBtn = "removeBtn";
        String userName = "username";

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("continueBtn")).thenReturn(continueBtn);
        when(request.getParameter("removeBtn")).thenReturn(removeBtn);
        when(request.getParameter("username")).thenReturn(userName);
        when(userRepo.getUserByName(userName)).thenReturn(user);

        when(context.getRequestDispatcher("/")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("tooltip", String.format("<span class='text-danger'>Пользователя '%s' не существует</span>", userName));
        verify(context, times(1)).getRequestDispatcher("/");
        verify(dispatcher).forward(request, response);
    }
}
