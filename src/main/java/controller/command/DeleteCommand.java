package controller.command;

import model.service.PublicationService;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;

public class DeleteCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        new PublicationService().delete(Integer.parseInt(request.getParameter("pubId")));
        request.setAttribute("publications", new PublicationService().getAll());
        return new PagePathManager().getProperty("path.page.admin.catalog");
    }
}
