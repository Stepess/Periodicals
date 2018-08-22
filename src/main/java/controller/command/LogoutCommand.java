package controller.command;


import controller.utils.SessionRequestContent;

import model.service.resource.manager.PagePathManager;

public class LogoutCommand implements Command{
    @Override
    public String execute(SessionRequestContent content) {
        content.setInvalidateSession(true);
        //return new PagePathManager().getProperty("path.page.index");
        return "redirect:" + new PagePathManager().getProperty("path.page.index"); //TODO with redirect create new session??
    }
}
