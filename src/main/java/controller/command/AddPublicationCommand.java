package controller.command;

import controller.utils.DataValidationUtil;
import model.entity.Publication;
import model.exception.NotUniqueTitleEnException;
import model.exception.NotUniqueTitleUaException;
import model.service.PublicationService;
import model.service.builders.PublicationBuilder;
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
        Locale locale = (Locale)request.getSession().getAttribute("locale");
        ResourceManager regexManager = new RegexpManager(locale);
        DataValidationUtil validationUtil = new DataValidationUtil(locale);

        try{
            new PublicationService().checkDataUnique(request.getParameter("title_en"),
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

        Publication publication = new PublicationBuilder()
                .buildTitle(
                        request.getParameter("title_en") +
                                "/en" +
                                request.getParameter("title_ua") +
                                "/ua")
                .buildAuthor(request.getParameter("author"))
                .buildGenre(request.getParameter("genre_en") +
                        "/en" +
                        request.getParameter("genre_ua") +
                        "/ua")
                .buildPrice(BigDecimal.valueOf(Double.parseDouble(request.getParameter("price"))))
                .buildDescription(request.getParameter("description_en") +
                        "/en" +
                        request.getParameter("description_ua") +
                        "/ua")
                .build();

        new PublicationService().setInDb(publication);
        request.setAttribute("publications", new PublicationService().getAllMultiLanguagePublication());
        return new PagePathManager().getProperty("path.page.admin.catalog");
    }

}




