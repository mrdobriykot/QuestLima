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

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IndexServletTest {

    @Mock
    private UserRepo userRepo;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private List<User> users;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext context;
    @Mock
    private RequestDispatcher dispatcher;
    @Spy
    private IndexServlet servlet;

    @BeforeEach
    void setup() throws ServletException {
        when(servletConfig.getServletContext()).thenReturn(context);
        servlet.init(servletConfig);
    }


    @Test
    void doPostTest() throws ServletException, IOException {
        when(context.getAttribute(argThat("userRepo"::equals))).thenReturn(userRepo);
        when(userRepo.getUsers()).thenReturn(users);
        when(context.getRequestDispatcher("/jsp/index.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("users", users);
        verify(context, times(1)).getRequestDispatcher("/jsp/index.jsp");
        verify(dispatcher).forward(request, response);
    }
}
