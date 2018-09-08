package controller.filters;


import model.entity.User;
import model.exception.AccessDeniedException;
import controller.utils.SecurityUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        SecurityUtils utils = new SecurityUtils();
        String urlPattern = request.getRequestURI();
        String userRole = (String) session.getAttribute("role");


        if (utils.isSecurityPage(urlPattern)) {
            boolean hasPermission = utils.hasPermission(urlPattern, userRole);
            if (!hasPermission) {
                throw new AccessDeniedException();
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}


/*
 if (utils.isSecurityPage(request)){
            if (userRole == null) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");

            } else {
                System.out.println(userRole);
                boolean hasPermission = utils.hasPermission(request);
                System.out.println("hello after boolean haspermission");
                if (!hasPermission) {
                    System.out.println("hellor from if");
                    throw new AccessDeniedException();
                }
            }
            }
 */