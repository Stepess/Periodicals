package controller.command;

import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.ResourceManager;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ResourceManager manager = new PagePathManager();
        return manager.getProperty("path.page.index");
    }
}
