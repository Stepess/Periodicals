package controller.command;


import controller.utils.SessionRequestContent;
import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.ResourceManager;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {

    //TODO guess how to delete
    @Override
    public String execute(HttpServletRequest request) {
        ResourceManager manager = new PagePathManager();
        return manager.getProperty("path.page.index");
    }
}
