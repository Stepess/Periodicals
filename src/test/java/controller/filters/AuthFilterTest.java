package controller.filters;

import model.exception.AccessDeniedException;
import model.service.UserService;
import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

public class AuthFilterTest {

    @Test(expected = AccessDeniedException.class)
    public void WhenRoleInSessionAndRoleInURIAreDifferentThenThrownAccessDeniedException() throws IOException, ServletException {
        final AuthFilter filter = new AuthFilter();
        final FilterChain filterChain = mock(FilterChain.class);

        String role = "guest";
        String commandUri = "/user/deleteSubscription";


        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);

        when(request.getRequestURI()).thenReturn(commandUri);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn(role);

        filter.doFilter(request, response, filterChain);
    }

    @Test(expected = AccessDeniedException.class)
    public void WhenUserTryDeletePublicationThenThrownAccessDeniedException() throws IOException, ServletException {
        final AuthFilter filter = new AuthFilter();
        final FilterChain filterChain = mock(FilterChain.class);

        String login = "loginTest";
        String role = "user";
        String commandUri = "/user/deletePublication";

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);

        when(request.getRequestURI()).thenReturn(commandUri);
        when(session.getAttribute("login")).thenReturn(login);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn(role);

        filter.doFilter(request, response, filterChain);
    }
}