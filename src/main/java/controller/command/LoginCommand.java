package controller.command;

import model.entity.User;
import model.service.UserService;
import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Provides login process
 */
public class LoginCommand implements Command {
    private final static Logger log = LogManager.getLogger(User.class);

    /**
     * Checks for user existence, checks if pair "login-password" is correct.
     * If all checks passed then user will be logged: set user's data in session and user's login in {@code ServletContext}
     *
     * @param request request represents http request obtained from client
     * @return path to which will be forwarded user's request and response
     */
    @Override
    public String execute(HttpServletRequest request) {
        ResourceManager manager = new PagePathManager();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            return manager.getProperty("path.page.login");
        }

        UserService loginService = new UserService();

        if (!isUserRoleGuest(request)) {
            log.warn("User that already logged tried to sign in");
            return "redirect:/" + loginService.getUserRole(login).toString().toLowerCase()
                    + manager.getProperty("path.command.user.catalog");
        }

        if (!loginService.isUserExist(login)) {
            setMessageToRequest(request, "wrongLogin", "message.auth.wrong.login");
            return manager.getProperty("path.page.login");
        }

        if (loginService.checkUserPassword(login, loginService.MD5(password))) {

            invalidateUserSessionIfAlreadyLogged(request, login);
            loginUser(request, login, loginService);

            log.info("User " + login + " has been logged");
            return "redirect:/" + loginService.getUserRole(login).toString().toLowerCase()
                    + "/catalog";
        } else {
            setMessageToRequest(request, "wrongPassword", "message.auth.wrong.password");
        }
        return manager.getProperty("path.page.login");
    }

    private boolean isUserRoleGuest(HttpServletRequest request) {
        return request.getSession().getAttribute("role").equals(User.RoleEnum.GUEST.getValue());
    }

    private void invalidateUserSessionIfAlreadyLogged(HttpServletRequest request, String login) {
        if (request.getSession().getServletContext().getAttribute(login) != null) {
            ((HttpSession) request.getSession().getServletContext().getAttribute(login)).invalidate();
        }
    }

    private void setMessageToRequest(HttpServletRequest request, String attributeName, String messagePropertyKey) {
        request.setAttribute(attributeName,
                new MessageManager((Locale) request.getSession().getAttribute("locale"))
                        .getProperty(messagePropertyKey));
    }

    private void loginUser(HttpServletRequest request, String login, UserService loginService) {
        request.getSession().setAttribute("login", login);
        request.getSession().setAttribute("role", loginService.getUserRole(login).getValue());
        request.getSession().getServletContext().setAttribute(login, request.getSession());
    }
}

