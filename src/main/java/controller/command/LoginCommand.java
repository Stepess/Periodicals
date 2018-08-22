package controller.command;

import model.entity.User;
import model.service.UserService;
import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.ResourceManager;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Map;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
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
            loginUser(request, login);

            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("role", loginService.getUserRole(login).getValue());
            return "redirect:/" +  loginService.getUserRole(login).toString().toLowerCase()
                    + manager.getProperty("path.page.main");
        } else {

            System.out.println(new MessageManager(new Locale(((String) request.getSession().getAttribute("language")).substring(0,2), ((String) request.getSession().getAttribute("language")).substring(3,5)))
                    .getProperty("message.wrong.login.password"));

            request.setAttribute("errorLoginPassMessage",
                    new MessageManager(new Locale(((String) request.getSession().getAttribute("language")).substring(0,2), ((String) request.getSession().getAttribute("language")).substring(3,5)))
                    .getProperty("message.wrong.login.password"));
        }
        return manager.getProperty("path.page.index");
    }

    private void loginUser(HttpServletRequest request, String login) {
        Map<String, Object> loginedUsers = (Map<String, Object>) request.getSession().getServletContext().getAttribute("loginedUsers");
        if (loginedUsers.get(login) != null){
            ((HttpSession)loginedUsers.get(login)).invalidate();
        }
        loginedUsers.put(login, request.getSession());
    }


}

/*private void loginUser(HttpServletRequest request, String login) {
        Map<String, Object> loginedUsers = (Map<String, Object>) request.getSession().getServletContext().getAttribute("loginedUsers");

        if (loginedUsers == null) {
            loginedUsers = new ConcurrentHashMap<>();
            loginedUsers.put(login, request.getSession());
            request.getSession().getServletContext().setAttribute("loginedUsers", loginedUsers);
        } else {
        if (loginedUsers.get(login) != null){
                ((HttpSession)loginedUsers.get(login)).invalidate();
            }
            loginedUsers.put(login, request.getSession());

        }
    }*/

