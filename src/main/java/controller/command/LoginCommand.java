package controller.command;


import controller.utils.SessionRequestContent;

import model.entity.User;
import model.service.UserService;
import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.ResourceManager;

public class LoginCommand implements Command {
    @Override
    public String execute(SessionRequestContent content) {
        ResourceManager manager = new PagePathManager();

        String login = content.getRequestParameter("login");//request.getParameter("login");
        String password =  content.getRequestParameter("password");//request.getParameter("password");

        if (login == null || password == null){
            return manager.getProperty("path.page.index");
        }

        UserService loginService = new UserService();

        if (!content.getSessionAttribute("role").equals(User.RoleEnum.GUEST.getValue())) {
            System.out.println("hello from if");
            return "redirect:/" +  loginService.getUserRole(login).toString().toLowerCase()
                    + manager.getProperty("path.page.main");
        }





        if (loginService.checkLoginPassword(login, password)) {
            /*content.addAttributeToSetSession("login", login);
            content.addAttributeToSetSession("role", loginService.getUserRole(login).getValue());*/
            content.addAttributeToSession("login", login);
            content.addAttributeToSession("role", loginService.getUserRole(login).getValue());
            return "redirect:/" +  loginService.getUserRole(login).toString().toLowerCase()
                    + manager.getProperty("path.page.main");
        } else {
            content.addAttributeToRequest("errorLoginPassMessage", "Wrong login password");
            //request.setAttribute("errorLoginPassMessage", "Wrong login password");
        }
        return manager.getProperty("path.page.index");
    }
}
