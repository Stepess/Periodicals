package controller.command;

import model.entity.Publication;
import model.service.PublicationService;
import model.service.resource.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class DeletePublicationCommand implements Command {
    private final static Logger log = LogManager.getLogger(Publication.class);

    @Override
    public String execute(HttpServletRequest request) {
        if(! new PublicationService().deletePublication(Integer.parseInt(request.getParameter("pubId")))){
            log.error("Attempt to delete publication from database has failed");
            throw new RuntimeException(
                    new MessageManager((Locale)request.getSession().getAttribute("locale"))
                            .getProperty("message.changes.not.accepted"));
        }
        log.info("Publication " + request.getParameter("title") + " has been deleted" );
        return request.getParameter("command")+"?"+request.getParameter("query");
    }
}
