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


public class LoginCommand implements Command {
    private final static Logger log = LogManager.getLogger(User.class);

    @Override
    public String execute(HttpServletRequest request) {//TODO change logic separetly check login and password
        ResourceManager manager = new PagePathManager();

        String login = request.getParameter("login");
        String password =  request.getParameter("password");

        if (login == null || password == null){
            return manager.getProperty("path.page.login");
        }

        UserService loginService = new UserService();

        if (!request.getSession().getAttribute("role").equals(User.RoleEnum.GUEST.getValue())) {
            log.warn("User that already logged tried to sign in");
            return "redirect:/" +  loginService.getUserRole(login).toString().toLowerCase()
                    + manager.getProperty("path.command.user.catalog");
        }

        if (! loginService.isUserExist(login)) {
            request.setAttribute("wrongLogin",
                    new MessageManager((Locale)request.getSession().getAttribute("locale"))
                            .getProperty("message.auth.wrong.login"));
            return  manager.getProperty("path.page.login");
        }

        if (loginService.checkUserPassword(login, loginService.MD5(password))) {
            if (request.getSession().getServletContext().getAttribute(login) != null){
                ((HttpSession) request.getSession().getServletContext().getAttribute(login)).invalidate();
            }
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("role", loginService.getUserRole(login).getValue());
            request.getSession().getServletContext().setAttribute(login, request.getSession());
            log.info("User " + login + " has been logged");
            return "redirect:/" +  loginService.getUserRole(login).toString().toLowerCase()
                    + "/catalog";
        } else {
            request.setAttribute("wrongPassword",
                    new MessageManager((Locale)request.getSession().getAttribute("locale"))
                            .getProperty("message.auth.wrong.password"));
        }
        return manager.getProperty("path.page.login");
    }
}

