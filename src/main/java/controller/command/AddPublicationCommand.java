package controller.command;

import model.entity.Publication;
import model.service.PublicationService;
import model.service.builders.PublicationBuilder;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class AddPublicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (isDataValid(request)) {
            Publication publication = new PublicationBuilder()
                    .buildTitle(
                            request.getParameter("title_en")+
                                    "/en"+
                                    request.getParameter("title_ua")+
                                    "/ua")
                    .buildAuthor(request.getParameter("author"))
                    .buildGenre(request.getParameter("genre_en")+
                            "/en"+
                            request.getParameter("genre_ua")+
                            "/ua")
                    .buildPrice(BigDecimal.valueOf(Double.parseDouble(request.getParameter("price"))))
                    .buildDescription(request.getParameter("description_en")+
                            "/en"+
                            request.getParameter("description_ua")+
                            "/ua")
                    .build();
            new PublicationService().setInDb(publication);
        }
        return new PagePathManager().getProperty("path.page.add.publication");
    }

    private boolean isDataValid(HttpServletRequest request) {
        return true;
    }
}




