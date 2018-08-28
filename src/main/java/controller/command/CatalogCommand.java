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
            PublicationService publicationService = new PublicationService();
            int currentPage;
            if (request.getParameter("currentPage")!=null) {
                currentPage = Integer.valueOf(request.getParameter("currentPage"));
            } else {
                currentPage=1;
            }

            int recordsPerPage;
            if (request.getParameter("recordsPerPage") != null) {
                recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
            } else {
                recordsPerPage = 5;
            }

            int rows = publicationService.getNumberOfPublication();

            int nOfPages = rows / recordsPerPage;

            if (nOfPages % recordsPerPage > 0) {
                nOfPages++;
            }


            int start = currentPage * recordsPerPage - recordsPerPage;


            request.setAttribute("noOfPages", nOfPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("recordsPerPage", recordsPerPage);
            request.setAttribute("publications", publicationService.getPaginatedList(start, recordsPerPage));
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
