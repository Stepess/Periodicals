package controller.command;

import controller.utils.PaginationUtil;
import model.service.PublicationService;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class CatalogCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        PublicationService publicationService = new PublicationService();
        Map<String, Integer> paginationParameters = new PaginationUtil().calculatePaginationParameters(request);
        request.setAttribute("paginationParameters", paginationParameters);
        request.setAttribute("publications", publicationService.getPaginatedList(paginationParameters.get("start"),
                paginationParameters.get("recordsPerPage")));

        if ("admin".equals(request.getSession().getAttribute("role"))) {
            return new PagePathManager().getProperty("path.page.admin.catalog");
        } else {
            Locale locale = (Locale)request.getSession().getAttribute("locale");
            request.setAttribute("publications",
                    publicationService.getPaginatedList(paginationParameters.get("start"),
                            paginationParameters.get("recordsPerPage"))
                            .stream()
                    .map(dto -> dto.convertToInternationalizedEntity(locale))
                    .collect(Collectors.toList()));
            return new PagePathManager().getProperty("path.page.periodicals");
        }
    }
}
/* Map<String, Integer> paginationParameters = new PaginationUtil().calculatePaginationParameters(request);

            System.out.println(paginationParameters);
            request.setAttribute("noOfPages", paginationParameters.get("noOfPages"));
            request.setAttribute("currentPage", paginationParameters.get("currentPage"));
            request.setAttribute("recordsPerPage", paginationParameters.get("recordsPerPage"));
            request.setAttribute("publications", publicationService.getPaginatedList(paginationParameters.get("start"),
                    paginationParameters.get("recordsPerPage")));*/