package controller.command;

import controller.utils.DataValidationUtil;
import model.entity.DTO.PublicationDTO;
import model.entity.Publication;
import model.exception.NotUniqueTitleEnException;
import model.exception.NotUniqueTitleUaException;
import model.service.PublicationService;
import model.service.builders.PublicationBuilder;
import model.service.builders.PublicationDtoBuilder;
import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.RegexpManager;
import model.service.resource.manager.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Locale;

public class EditPublicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        //Publication publication = new PublicationService().getById(Integer.parseInt(request.getParameter("pubId")));
        //request.setAttribute("publication", publication);
        //return new PagePathManager().getProperty("")
        Locale locale = (Locale)request.getSession().getAttribute("locale");
        DataValidationUtil validationUtil = new DataValidationUtil(locale);
        ResourceManager regexManager = new RegexpManager(locale);

        /*try{
            new PublicationService().checkDataUnique(request.getParameter("title_en"),
                    request.getParameter("title_ua"));
        } catch (NotUniqueTitleEnException ex) {
            request.setAttribute("wrongtitle_en", new MessageManager(new Locale("en", "US"))
                    .getProperty("message.not.unique.title.en"));
        } catch (NotUniqueTitleUaException ex) {
            request.setAttribute("wrongtitle_ua", new MessageManager(new Locale("en", "US"))
                    .getProperty("message.not.unique.title.ua"));
        }*/

        // Pattern pattern = Pattern.compile("^[А-ЩЄІЇЮЯҐ0-9]+[А-ЩЄІЇЮЯҐа-щєіїьюяґ0-9'#&%!,()№:\\*\\s\\-\\$\\.]*$")


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
                return new PagePathManager().getProperty("path.page.edit.publication");
            }
        }

        PublicationDTO publicationDTO = new PublicationDtoBuilder(Integer.parseInt(request.getParameter("pubId")))
                .buildTitleEn(request.getParameter("title_en"))
                .buildTitleUa(request.getParameter("title_ua"))
                .buildAuthor(request.getParameter("author"))
                .buildPrice(BigDecimal.valueOf(Double.parseDouble(request.getParameter("price"))))
                .buildGenreEn(request.getParameter("genre_en"))
                .buildGenreUa(request.getParameter("genre_ua"))
                .buildDescriptionEn(request.getParameter("description_en"))
                .buildDescriptionUa(request.getParameter("description_ua"))
                .build();

        System.out.println(publicationDTO);

        /*publication = new PublicationBuilder()
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
                .build();*/
        new PublicationService().setPublicationDto(publicationDTO);
        request.setAttribute("publications", new PublicationService().getAllMultiLanguagePublication());
        return new PagePathManager().getProperty("path.page.admin.catalog");
    }
}
