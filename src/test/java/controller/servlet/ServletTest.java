package controller.servlet;

import controller.command.Command;
import controller.command.CommandFactory;
import model.entity.DTO.SubscriptionDto;
import model.entity.Subscription;
import model.service.SubscriptionService;
import model.service.UserService;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServletTest {

    @Test
    public void WhenChangeLanguageRequestThenForwardToIndexJsp() throws ServletException, IOException {
        final Servlet servlet = new Servlet();
        String commandUri = "/user/changeLanguage";
        String path = "/index.jsp";

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestURI()).thenReturn(commandUri);
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        servlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(path);
        verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void WhenUnregisteredUserTryToSignInThenForwardToLoginPageWithWrongLoginAttribute() throws ServletException, IOException {
        final Servlet servlet = new Servlet();
        String login = "loginTest";
        String role = "guest";
        String commandUri = "/guest/login";
        String path = "/WEB-INF/view/login.jsp";

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        final HttpSession session = mock(HttpSession.class);
        final UserService service = mock(UserService.class);

        when(request.getRequestURI()).thenReturn(commandUri);
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getParameter(anyString())).thenReturn(login);
        when(request.getSession()).thenReturn(session);
        when(service.isUserExist(login)).thenReturn(false);
        when(session.getAttribute("role")).thenReturn(role);
        when(session.getAttribute("locale")).thenReturn(new Locale("en", "US"));
        servlet.doPost(request, response);

        verify(request, times(1)).getRequestDispatcher(path);
        verify(request, times(2)).getSession();
        verify(request, times(2)).getParameter(anyString());
        verify(request, times(1)).setAttribute(eq("wrongLogin"), anyString());
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void GivenUserWithoutSubscriptionsWhenSHowSubscriptionsRequestThenForwardToSubscriptionsJspWithFailAttribute()
            throws ServletException, IOException {
        final Servlet servlet = new Servlet();
        String login = "loginTest";
        String state = "PAID";
        String commandUri = "/user/subscriptions";
        String path = "/WEB-INF/view/user/subscriptions.jsp";

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        final HttpSession session = mock(HttpSession.class);
        final SubscriptionService service = mock(SubscriptionService.class);

        when(request.getRequestURI()).thenReturn(commandUri);
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getParameter(anyString())).thenReturn(login);
        when(request.getSession()).thenReturn(session);
        when(service.getAllUserSubscription(login, state)).thenReturn(new ArrayList<SubscriptionDto>());
        when(session.getAttribute("locale")).thenReturn(new Locale("en", "US"));
        servlet.doPost(request, response);

        verify(request, times(1)).getRequestDispatcher(path);
        verify(request, times(2)).getSession();
        verify(request, times(1)).getParameter(anyString());
        verify(request, times(1)).setAttribute(eq("fail"), anyString());
        verify(dispatcher).forward(request, response);
    }
}