package controller.command;

import model.service.UserService;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class ReplenishCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        new UserService().replenishAccount((String)request.getSession().getAttribute("login"),
                BigDecimal.valueOf(Double.parseDouble(request.getParameter("money")==null? "0.0" : request.getParameter("money"))));
        request.setAttribute("user", new UserService().getUserByLogin(
                (String)request.getSession().getAttribute("login")));
        return new PagePathManager().getProperty("path.page.user.profile");
    }
}
