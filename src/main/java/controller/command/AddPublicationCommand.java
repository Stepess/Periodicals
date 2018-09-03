package controller.command;

import controller.utils.DataValidationUtil;
import model.entity.DTO.PublicationDto;
import model.exception.NotUniqueTitleEnException;
import model.exception.NotUniqueTitleUaException;
import model.service.PublicationService;
import model.service.builders.PublicationDtoBuilder;
import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.RegexpManager;
import model.service.resource.manager.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Locale;


public class AddPublicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        ResourceManager regexManager = new RegexpManager(locale);
        DataValidationUtil validationUtil = new DataValidationUtil(locale);
        PublicationService publicationService = new PublicationService();

        if (validationUtil.isEmptyRequest(request)) {
            new PagePathManager().getProperty("path.page.add.publication");
        }

        try {
            publicationService.checkTitlesUnique(request.getParameter("title_en"),
                    request.getParameter("title_ua"));
        } catch (NotUniqueTitleEnException ex) {
            request.setAttribute("wrongtitle_en", new MessageManager(locale)
                    .getProperty("message.not.unique.title.en"));
        } catch (NotUniqueTitleUaException ex) {
            request.setAttribute("wrongtitle_ua", new MessageManager(locale)
                    .getProperty("message.not.unique.title.ua"));
        }

        validationUtil.isDataValid(request, "title_en", regexManager.getProperty("publication.title_en"));
        validationUtil.isDataValid(request, "title_ua", regexManager.getProperty("publication.title_ua"));
        validationUtil.isDataValid(request, "author", regexManager.getProperty("publication.author"));
        validationUtil.isDataValid(request, "genre_en", regexManager.getProperty("publication.genre_en"));
        validationUtil.isDataValid(request, "genre_ua", regexManager.getProperty("publication.genre_ua"));
        validationUtil.isDataValid(request, "price", regexManager.getProperty("publication.price"));
        validationUtil.isDataValid(request, "description_en", regexManager.getProperty("publication.description_en"));
        validationUtil.isDataValid(request, "description_ua", regexManager.getProperty("publication.description_ua"));

        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String elementName = attributeNames.nextElement();
            if (elementName.contains("wrong")) {
                return new PagePathManager().getProperty("path.page.add.publication");
            }
        }

        PublicationDto publicationDto = new PublicationDtoBuilder()
                .buildTitleEn(request.getParameter("title_en"))
                .buildTitleUa(request.getParameter("title_ua"))
                .buildAuthor(request.getParameter("author"))
                .buildGenreEn(request.getParameter("genre_en"))
                .buildGenreUa(request.getParameter("genre_ua"))
                .buildPrice(BigDecimal.valueOf(Double.parseDouble(request.getParameter("price"))))
                .buildDescriptionEn(request.getParameter("description_en"))
                .buildDescriptionUa(request.getParameter("description_ua"))
                .build();

        if (! publicationService.addPublication(publicationDto)){
            throw new RuntimeException(new MessageManager(locale).getProperty("message.changes.not.accepted"));
        }

        request.setAttribute("status", new MessageManager(locale).getProperty("message.publication.added"));
        return new PagePathManager().getProperty("path.command.admin.catalog");
    }
}




