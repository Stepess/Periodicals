package controller.command;

import model.entity.User;
import model.service.UserService;
import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {//TODO change logic separetly check login and password
        ResourceManager manager = new PagePathManager();

        String login = request.getParameter("login");
        String password =  request.getParameter("password");

        if (login == null || password == null){
            return manager.getProperty("path.page.index");
        }

        UserService loginService = new UserService();

        if (!request.getSession().getAttribute("role").equals(User.RoleEnum.GUEST.getValue())) {
            return "redirect:/" +  loginService.getUserRole(login).toString().toLowerCase()
                    + manager.getProperty("path.page.main");
        }
        if (loginService.checkLoginPassword(login, password)) {
            if (request.getSession().getServletContext().getAttribute(login) != null){
                ((HttpSession) request.getSession().getServletContext().getAttribute(login)).invalidate();
            }
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("role", loginService.getUserRole(login).getValue());
            request.getSession().getServletContext().setAttribute(login, request.getSession());
            return "redirect:/" +  loginService.getUserRole(login).toString().toLowerCase()
                    + manager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage",
                    new MessageManager((Locale)request.getSession().getAttribute("locale"))
                            .getProperty("message.wrong.login.password"));
        }
        return manager.getProperty("path.page.index");
    }
}

