package controller.filters;


import model.exception.AccessDeniedException;
import controller.utils.SecurityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    private final static Logger log = LogManager.getLogger(SecurityUtils.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpSession session = request.getSession();

        String urlPattern = request.getRequestURI();
        String userRole = (String) session.getAttribute("role");
        SecurityUtils utils = new SecurityUtils();

        if (utils.isSecurityPage(urlPattern)) {
            boolean hasPermission = utils.hasPermission(urlPattern, userRole);
            if (!hasPermission) {
                String login = (String) request.getSession().getAttribute("login");
                log.warn("Unauthorized access attempt, login: " + (login == null ? "unknown user" : login));
                throw new AccessDeniedException();
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}