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

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RestartServletTest {

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
    private RestartServlet servlet;

    @BeforeEach
    void setup() throws ServletException {
        when(servletConfig.getServletContext()).thenReturn(context);
        when(context.getAttribute("userRepo")).thenReturn(userRepo);
        servlet.init(servletConfig);
    }

    @Test
    void doPostTest() throws ServletException, IOException {
        String userName = "userName";
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(argThat("userName"::equals))).thenReturn(userName);
        when(userRepo.getUserByName(userName)).thenReturn(user);
        when(context.getRequestDispatcher("/action")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(userRepo, times(1)).getUserByName(userName);
        verify(context, times(1)).getRequestDispatcher("/action");
        verify(dispatcher).forward(request, response);
    }
}
