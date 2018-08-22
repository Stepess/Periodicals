package controller.command;


import controller.utils.SessionRequestContent;
import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.ResourceManager;

public class DefaultCommand implements Command {

    //TODO guess how to delete
    @Override
    public String execute(SessionRequestContent content) {
        ResourceManager manager = new PagePathManager();
        return manager.getProperty("path.page.index");
    }
}
