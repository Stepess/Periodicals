package controller.command;

import model.entity.DTO.PublicationDto;
import model.entity.Publication;
import model.service.PublicationService;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CatalogCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        if ("admin".equals(request.getSession().getAttribute("role"))) {
            request.setAttribute("publications", new PublicationService().getAllMultiLanguagePublication());
            return new PagePathManager().getProperty("path.page.admin.catalog");
        } else {
            List<PublicationDto> list = new PublicationService().getAll();
            List<Publication> list1 = new ArrayList<>();
            Locale locale = (Locale)request.getSession().getAttribute("locale");
            for (PublicationDto dto: list){
                list1.add(dto.convertToInternationalizedEntity(locale));
            }
            request.setAttribute("publications", list1);
            return new PagePathManager().getProperty("path.page.periodicals");
        }
    }
}
