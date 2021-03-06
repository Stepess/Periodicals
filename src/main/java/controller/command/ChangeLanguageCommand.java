package controller.command;

import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return new PagePathManager().getProperty("path.page.index");
    }
}
