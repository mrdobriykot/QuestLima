package sheff.module3.game.rpgquest.servlet;

import sheff.module3.game.rpgquest.entity.Location;
import sheff.module3.game.rpgquest.entity.Person;
import sheff.module3.game.rpgquest.entity.User;
import sheff.module3.game.rpgquest.repository.LocationRepo;
import sheff.module3.game.rpgquest.repository.PersonRepo;
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
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ActionServletTest {

    @Mock
    private UserRepo userRepo;
    @Mock
    private Location location;
    @Mock
    private LocationRepo locationRepo;
    @Mock
    private List<Location> locationsForMovement;
    @Mock
    private List<Person> personsOnLocation;
    @Mock
    private PersonRepo personRepo;
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
    private ActionServlet servlet;

    @BeforeEach
    void setup() throws ServletException {
        when(servletConfig.getServletContext()).thenReturn(context);
        when(context.getAttribute("userRepo")).thenReturn(userRepo);
        when(context.getAttribute("personRepo")).thenReturn(personRepo);
        servlet.init(servletConfig);
    }

    @Test
    void doPost_when_location_is_last_invoke_set_attribute() throws ServletException, IOException {
        int selectedLocationId = 14;
        String userName = "userName";
        String locationIdParam = "14";

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("userName")).thenReturn(userName);
        when(request.getParameter("locationId")).thenReturn(locationIdParam);
        when(userRepo.getUserByName(userName)).thenReturn(user);
        when(user.getLocationRepo()).thenReturn(locationRepo);
        when(locationRepo.getLocationById(selectedLocationId)).thenReturn(location);
        when(location.getLocationsForMovement()).thenReturn(locationsForMovement);
        when(personRepo.getPersonsByLocationId(selectedLocationId)).thenReturn(personsOnLocation);
        when(personsOnLocation.isEmpty()).thenReturn(false);
        when(context.getRequestDispatcher("/jsp/action.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("win", true);

    }

        @Test
    void doPost_when_persons_on_location_is_empty_invoke_remove_attribute() throws ServletException, IOException {
        int selectedLocationId = 3;
        String userName = "userName";
        String locationIdParam = "3";
        String locationIdForContinue = "2";

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("userName")).thenReturn(userName);
        when(request.getParameter("locationId")).thenReturn(locationIdParam);
        when(request.getAttribute("locationIdForContinue")).thenReturn(locationIdForContinue);
        when(userRepo.getUserByName(userName)).thenReturn(user);
        when(user.getLocationRepo()).thenReturn(locationRepo);
        when(locationRepo.getLocationById(selectedLocationId)).thenReturn(location);
        when(location.getLocationsForMovement()).thenReturn(locationsForMovement);
        when(personRepo.getPersonsByLocationId(selectedLocationId)).thenReturn(personsOnLocation);
        when(personsOnLocation.isEmpty()).thenReturn(true);

        when(context.getRequestDispatcher("/jsp/action.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(session).removeAttribute("personsOnLocation");
    }

    @Test
    void doPost_when_persons_on_location_not_empty_invoke_remove_attribute() throws ServletException, IOException {
        int selectedLocationId = 3;
        String userName = "userName";
        String locationIdParam = "3";
        String locationIdForContinue = "2";

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("userName")).thenReturn(userName);
        when(request.getParameter("locationId")).thenReturn(locationIdParam);
        when(request.getAttribute("locationIdForContinue")).thenReturn(locationIdForContinue);
        when(userRepo.getUserByName(userName)).thenReturn(user);
        when(user.getLocationRepo()).thenReturn(locationRepo);
        when(locationRepo.getLocationById(selectedLocationId)).thenReturn(location);
        when(location.getLocationsForMovement()).thenReturn(locationsForMovement);
        when(personRepo.getPersonsByLocationId(selectedLocationId)).thenReturn(personsOnLocation);
        when(personsOnLocation.isEmpty()).thenReturn(false);

        when(context.getRequestDispatcher("/jsp/action.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(session).setAttribute("personsOnLocation", personsOnLocation);
    }

    @Test
    void doPost_invoke_set_attribute_location() throws ServletException, IOException {
        int selectedLocationId = 3;
        String userName = "userName";
        String locationIdParam = "3";
        String locationIdForContinue = "2";

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("userName")).thenReturn(userName);
        when(request.getParameter("locationId")).thenReturn(locationIdParam);
        when(request.getAttribute("locationIdForContinue")).thenReturn(locationIdForContinue);
        when(userRepo.getUserByName(userName)).thenReturn(user);
        when(user.getLocationRepo()).thenReturn(locationRepo);
        when(locationRepo.getLocationById(selectedLocationId)).thenReturn(location);
        when(location.getLocationsForMovement()).thenReturn(locationsForMovement);

        when(context.getRequestDispatcher("/jsp/action.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(session).setAttribute("currentLocation", location);
    }

    @Test
    void doPost_invoke_set_attribute_locationsForMovement() throws ServletException, IOException {
        int selectedLocationId = 3;
        String userName = "userName";
        String locationIdParam = "3";
        String locationIdForContinue = "2";

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("userName")).thenReturn(userName);
        when(request.getParameter("locationId")).thenReturn(locationIdParam);
        when(request.getAttribute("locationIdForContinue")).thenReturn(locationIdForContinue);
        when(userRepo.getUserByName(userName)).thenReturn(user);
        when(user.getLocationRepo()).thenReturn(locationRepo);
        when(locationRepo.getLocationById(selectedLocationId)).thenReturn(location);
        when(location.getLocationsForMovement()).thenReturn(locationsForMovement);

        when(context.getRequestDispatcher("/jsp/action.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(session).setAttribute("locationsForMovement", locationsForMovement);
    }

    @Test
    void doPost_invoke_dispatcher_forward() throws ServletException, IOException {
        int selectedLocationId = 3;
        String userName = "userName";
        String locationIdParam = "3";
        String locationIdForContinue = "2";

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("userName")).thenReturn(userName);
        when(request.getParameter("locationId")).thenReturn(locationIdParam);
        when(request.getAttribute("locationIdForContinue")).thenReturn(locationIdForContinue);
        when(userRepo.getUserByName(userName)).thenReturn(user);
        when(user.getLocationRepo()).thenReturn(locationRepo);
        when(locationRepo.getLocationById(selectedLocationId)).thenReturn(location);
        when(location.getLocationsForMovement()).thenReturn(locationsForMovement);

        when(context.getRequestDispatcher("/jsp/action.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(context, times(1)).getRequestDispatcher("/jsp/action.jsp");
        verify(dispatcher).forward(request, response);
    }
}
