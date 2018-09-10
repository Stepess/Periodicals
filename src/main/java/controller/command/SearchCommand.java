package controller.command;

import controller.utils.PaginationUtil;

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
        Locale locale = (Locale) request.getSession().getAttribute("locale");

        Map<String, String[]> requestParameterMap = request.getParameterMap();

        Map<String, String> searchParameters = new HashMap<>();

        for (String s : requestParameterMap.keySet()) {
            if (requestParameterMap.get(s)[0] != null && !requestParameterMap.get(s)[0].isEmpty()) {
                searchParameters.put(s, requestParameterMap.get(s)[0]);
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


        return new PagePathManager().getProperty("path.page.search");
    }
}
