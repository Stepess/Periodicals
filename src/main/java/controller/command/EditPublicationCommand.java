package controller.command;

import controller.utils.DataValidationUtil;
import model.entity.DTO.PublicationDto;
import model.entity.Publication;
import model.service.PublicationService;
import model.service.builders.PublicationDtoBuilder;
import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.RegexpManager;
import model.service.resource.manager.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Locale;

public class EditPublicationCommand implements Command {
    private final static Logger log = LogManager.getLogger(Publication.class);

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = (Locale)request.getSession().getAttribute("locale");
        DataValidationUtil validationUtil = new DataValidationUtil(locale);
        ResourceManager regexManager = new RegexpManager(locale);

        if (request.getAttribute("publication") == null && !"edit".equals(request.getParameter("flag"))) {
            request.setAttribute("publication", new PublicationService().getById(
                    Integer.valueOf(request.getParameter("pubId"))));
            return new PagePathManager().getProperty("path.page.edit.publication");
        }

        validationUtil.isDataValid(request, "title_en", regexManager.getProperty("publication.title_en"));
        validationUtil.isDataValid(request, "title_ua", regexManager.getProperty("publication.title_ua"));
        validationUtil.isDataValid(request, "author", regexManager.getProperty("publication.author"));
        validationUtil.isDataValid(request, "genre_en", regexManager.getProperty("publication.genre_en"));
        validationUtil.isDataValid(request, "genre_ua", regexManager.getProperty("publication.genre_ua"));
        validationUtil.isDataValid(request, "price", regexManager.getProperty("publication.price"));
        validationUtil.isDataValid(request, "description_en", regexManager.getProperty("publication.description_en"));
        validationUtil.isDataValid(request, "description_ua", regexManager.getProperty("publication.description_ua"));

        PublicationDto publicationDTO = new PublicationDtoBuilder(Integer.parseInt(request.getParameter("pubId")))
                .buildTitleEn(request.getParameter("title_en"))
                .buildTitleUa(request.getParameter("title_ua"))
                .buildAuthor(request.getParameter("author"))
                .buildPrice(BigDecimal.valueOf(Double.parseDouble(request.getParameter("price"))))
                .buildGenreEn(request.getParameter("genre_en"))
                .buildGenreUa(request.getParameter("genre_ua"))
                .buildDescriptionEn(request.getParameter("description_en"))
                .buildDescriptionUa(request.getParameter("description_ua"))
                .build();

        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String elementName = attributeNames.nextElement();
            if (elementName.contains("wrong")) {
                request.setAttribute("publication" , publicationDTO);
                return new PagePathManager().getProperty("path.page.edit.publication");
            }
        }



        if (! new PublicationService().editPublication(publicationDTO)){
            log.error("Attempt to edit publication from database has failed");
            throw new RuntimeException(new MessageManager(locale).getProperty("message.changes.not.accepted"));
        }

        log.info("Publication " + request.getParameter("title_en") + " has been edited");
        request.setAttribute("status", new MessageManager(locale).getProperty("message.publication.changed"));
        return request.getParameter("command")+"?"+request.getParameter("query");
    }
}
