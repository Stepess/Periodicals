package controller.command;

import model.entity.User;
import model.service.UserService;
import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import org.apache.log4j.*;

public class LoginCommand implements Command {
    final static Logger log = Logger.getLogger(LoginCommand.class);

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
            return "redirect:/" +  loginService.getUserRole(login).toString().toLowerCase()
                    + manager.getProperty("path.command.user.catalog");
        }

        if (! loginService.isUserExist(login)) {
            request.setAttribute("wrongLogin",
                    new MessageManager((Locale)request.getSession().getAttribute("locale"))
                            .getProperty("message.auth.wrong.login"));
            log.error("Attempt to sign in in unregistered account");
            return  manager.getProperty("path.page.login");
        }

        if (loginService.checkUserPassword(login, loginService.MD5(password))) {
            if (request.getSession().getServletContext().getAttribute(login) != null){
                ((HttpSession) request.getSession().getServletContext().getAttribute(login)).invalidate();
            }
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("role", loginService.getUserRole(login).getValue());
            request.getSession().getServletContext().setAttribute(login, request.getSession());
            /*return "redirect:/" +  loginService.getUserRole(login).toString().toLowerCase()
                    + manager.getProperty("path.command.user.catalog");*/
            return manager.getProperty("path.command.user.catalog");
        } else {
            request.setAttribute("wrongPassword",
                    new MessageManager((Locale)request.getSession().getAttribute("locale"))
                            .getProperty("message.auth.wrong.password"));
        }
        return manager.getProperty("path.page.login");
    }
}

