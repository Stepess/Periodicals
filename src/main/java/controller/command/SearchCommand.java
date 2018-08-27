package controller.command;

import model.exception.NothingFoundException;
import model.service.PublicationService;
import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

        Map<String, String[]> requestParameterMap = request.getParameterMap();

        Map<String, String> searchParameters = new HashMap<>();

        for (String s: requestParameterMap.keySet()) {
            if (requestParameterMap.get(s)[0]!=null && !requestParameterMap.get(s)[0].isEmpty()){
                searchParameters.put(s,requestParameterMap.get(s)[0]);
            }
        }

        try {
            request.setAttribute("publications", new PublicationService().search(searchParameters));
        } catch (NothingFoundException e){
            request.setAttribute("fail", new MessageManager((Locale)request.getSession().getAttribute("locale")).getProperty("message.nothing.found"));
        }




        return new PagePathManager().getProperty("path.page.periodicals");
    }
}
