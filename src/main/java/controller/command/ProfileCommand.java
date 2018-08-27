package controller.command;

import model.service.UserService;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;

public class ProfileCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("user", new UserService().getUserByLogin(
                (String)request.getSession().getAttribute("login")));
        return new PagePathManager().getProperty("path.page.user.profile");
    }
}
