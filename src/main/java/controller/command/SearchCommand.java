package controller.command;

import controller.utils.PaginationUtil;
import model.entity.DTO.PublicationDto;
import model.entity.Publication;
import model.exception.NothingFoundException;
import model.service.PublicationService;
import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

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

        String role = (String)request.getSession().getAttribute("role");


        Map<String, String[]> requestParameterMap = request.getParameterMap();

        Map<String, String> searchParameters = new HashMap<>();

        for (String s: requestParameterMap.keySet()) {
            if (requestParameterMap.get(s)[0]!=null && !requestParameterMap.get(s)[0].isEmpty()){
                searchParameters.put(s,requestParameterMap.get(s)[0]);
                request.setAttribute(s, requestParameterMap.get(s)[0]);
            }
        }

        PublicationService publicationService = new PublicationService();
        Map<String, Integer> paginationParameters;
        try {
            paginationParameters = new PaginationUtil().calculatePaginationParameters(request,
                    publicationService.getNumberOfSearchedPublication(searchParameters));
            request.setAttribute("paginationParameters", paginationParameters);
        } catch (NothingFoundException e) {
            request.setAttribute("fail", new MessageManager((Locale) request.getSession().getAttribute("locale")).getProperty("message.nothing.found"));
            return new PagePathManager().getProperty("path.page.user.search");
        }



            Locale locale = (Locale) request.getSession().getAttribute("locale");
            request.setAttribute("publications",
                    publicationService.getPaginatedSearchList(searchParameters,
                            paginationParameters.get("start"),
                            paginationParameters.get("recordsPerPage"))
                            .stream()
                            .map(dto -> dto.convertToInternationalizedEntity(locale))
                            .collect(Collectors.toList()));
            //return new PagePathManager().getProperty("path.page.user.search");


        return new PagePathManager().getProperty("path.page.user.search");
    }
}



/*
* if ("admin".equals(role)) {
            request.setAttribute("publications", publicationService.getPaginatedSearchList(searchParameters,
                    paginationParameters.get("start"),
                    paginationParameters.get("recordsPerPage")));
            //return new PagePathManager().getProperty("path.page.admin.search");
        } else {
            Locale locale = (Locale) request.getSession().getAttribute("locale");
            request.setAttribute("publications",
                    publicationService.getPaginatedSearchList(searchParameters,
                            paginationParameters.get("start"),
                            paginationParameters.get("recordsPerPage"))
                            .stream()
                            .map(dto -> dto.convertToInternationalizedEntity(locale))
                            .collect(Collectors.toList()));
            //return new PagePathManager().getProperty("path.page.user.search");
        }*/


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
        }

    Map<String, String[]> requestParameterMap = request.getParameterMap();

    Map<String, String> searchParameters = new HashMap<>();

        for (String s: requestParameterMap.keySet()) {
                if (requestParameterMap.get(s)[0]!=null && !requestParameterMap.get(s)[0].isEmpty()){
                searchParameters.put(s,requestParameterMap.get(s)[0]);
                request.setAttribute(s, requestParameterMap.get(s)[0]);
                }
                }




        try {
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

        int rows = publicationService.getNumberOfSearchedPublication(searchParameters);

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
        nOfPages++;
        }
        System.out.println(rows);


        int start = currentPage * recordsPerPage - recordsPerPage;

        System.out.println(start);

        System.out.println(new PublicationService().getPaginatedSearchList(searchParameters, start, recordsPerPage));
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        request.setAttribute("publications",  new PublicationService().getPaginatedSearchList(searchParameters, start, recordsPerPage));
        return new PagePathManager().getProperty("path.page.admin.search");
        } else {
        List<PublicationDto> list = new PublicationService().search(searchParameters);
        List<Publication> list1 = new ArrayList<>();
        Locale locale = (Locale)request.getSession().getAttribute("locale");
        for (PublicationDto dto: list){
        list1.add(dto.convertToInternationalizedEntity(locale));
        }
        request.setAttribute("publications", list1);
        return new PagePathManager().getProperty("path.page.admin.search");
        }
        } catch (NothingFoundException e){
        request.setAttribute("fail", new MessageManager((Locale)request.getSession().getAttribute("locale")).getProperty("message.nothing.found"));
        }




        return new PagePathManager().getProperty("path.page.admin.search");*/
