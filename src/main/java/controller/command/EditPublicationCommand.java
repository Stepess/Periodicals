package controller.command;

import model.entity.Publication;
import model.service.PublicationService;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;

public class EditPublicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Publication publication = new PublicationService().getById(Integer.parseInt(request.getParameter("pubId")));
        request.setAttribute("publication", publication);
        //return new PagePathManager().getProperty("")
        return null;
    }
}
