package controller.command;


import model.entity.User;
import model.service.resource.manager.PagePathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command{
    private final static Logger log = LogManager.getLogger(User.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            log.info("User " + session.getAttribute("login") + " logged out");
            session.invalidate();
        }
        return "redirect:" + new PagePathManager().getProperty("path.page.index");
    }
}
