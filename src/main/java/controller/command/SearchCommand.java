package controller.command;

import controller.utils.DataValidationUtil;
import controller.utils.PaginationUtil;
import model.entity.DTO.PublicationDto;
import model.entity.Publication;
import model.exception.NothingFoundException;
import model.service.PublicationService;
import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.RegexpManager;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;
//TODO refactor
public class SearchCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        /*System.out.println(request.getParameter("title"));
        System.out.println(request.getParameter("genre"));
        System.out.println(request.getParameter("leftPriceBoundary"));
         System.out.println(request.getParameter("rightPriceBoundary"));*/

        /*BigDecimal leftPriceBoundary = new BigDecimal(request.getParameter("leftPriceBoundary"));//TODO refactor other
        BigDecimal rightPriceBoundary = new BigDecimal(request.getParameter("rightPriceBoundary"));//TODO refactor other
        if (leftPriceBoundary!=null && rightPriceBoundary!=null && leftPriceBoundary.compareTo(rightPriceBoundary) > 0) {
            request.setAttribute("fail", new MessageManager((Locale)request.getSession().getAttribute("locale"))
                    .getProperty("message.wrong.boundary"));
            return new PagePathManager().getProperty("path.page.periodicals");
        }*/

        /*
        System.out.println(locale);

        DataValidationUtil dataValidationUtil = new DataValidationUtil(locale);
        RegexpManager regexpManager = new RegexpManager(locale);

        dataValidationUtil.isDataValid(request, "title", regexpManager.getProperty("search.title"));
        dataValidationUtil.isDataValid(request, "genre", regexpManager.getProperty("search.genre"));
        dataValidationUtil.isDataValid(request, "leftPriceBoundary", regexpManager.getProperty("search.price"));
        dataValidationUtil.isDataValid(request, "rightPriceBoundary", regexpManager.getProperty("search.price"));

        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String elementName = attributeNames.nextElement();
            if (elementName.contains("wrong")) {
                return new PagePathManager().getProperty("path.page.search");
            }
        }*/

        Locale locale = (Locale) request.getSession().getAttribute("locale");

        Map<String, String[]> requestParameterMap = request.getParameterMap();

        Map<String, String> searchParameters = new HashMap<>();

        for (String s: requestParameterMap.keySet()) {
            if (requestParameterMap.get(s)[0]!=null && !requestParameterMap.get(s)[0].isEmpty()){
                searchParameters.put(s,requestParameterMap.get(s)[0]);
                //request.setAttribute(s, requestParameterMap.get(s)[0]);
            }
        }

        PublicationService publicationService = new PublicationService();
        Map<String, Integer> paginationParameters;
        try {
            paginationParameters = new PaginationUtil().calculatePaginationParameters(request,
                    publicationService.getNumberOfSearchedPublication(searchParameters));
            request.setAttribute("paginationParameters", paginationParameters);
        } catch (NothingFoundException e) {
            request.setAttribute("fail", new MessageManager(locale).getProperty("message.nothing.found"));
            return new PagePathManager().getProperty("path.page.search");
        }

            request.setAttribute("publications",
                    publicationService.getPaginatedSearchList(searchParameters,
                            paginationParameters.get("start"),
                            paginationParameters.get("recordsPerPage"))
                            .stream()
                            .map(dto -> dto.convertToInternationalizedEntity(locale))
                            .collect(Collectors.toList()));
            //return new PagePathManager().getProperty("path.page.user.search");


        return new PagePathManager().getProperty("path.page.search");
    }
}
