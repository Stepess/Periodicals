package controller.command;


import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class LogoutCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);//add false
        if (session != null) {
            /*Map<String, Object> map = (Map<String, Object>) session.getServletContext().getAttribute("loginedUsers");
            if (session.getAttribute("login") != null && map.containsKey(session.getAttribute("login")))
                map.remove(session.getAttribute("login"));*/
            /*request.getSession().getServletContext().removeAttribute((String) session.getAttribute("login"));*/
            session.invalidate();
        }
        return "redirect:" + new PagePathManager().getProperty("path.page.index"); //TODO with redirect create new session??
    }
}
