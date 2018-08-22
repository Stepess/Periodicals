package controller.command;

import model.service.PublicationService;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;

public class CatalogCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("publications", new PublicationService().getAll());
        return new PagePathManager().getProperty("path.page.periodicals");
    }
}
