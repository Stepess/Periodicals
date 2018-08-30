package controller.command;

import model.service.PublicationService;
import model.service.resource.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class DeletePublicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if(! new PublicationService().deletePublication(Integer.parseInt(request.getParameter("pubId")))){
            throw new RuntimeException(
                    new MessageManager((Locale)request.getSession().getAttribute("locale"))
                            .getProperty("message.changes.not.accepted"));
        }
        return request.getParameter("command")+"?"+request.getParameter("query");
    }
}
