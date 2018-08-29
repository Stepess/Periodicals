package controller.utils;

import model.service.PublicationService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class PaginationUtil {
    public Map<String, Integer> calculatePaginationParameters(HttpServletRequest request) {
        int currentPage =
                Integer.parseInt(getRequestParameterOrDefault(request, "currentPage", "1"));

        int recordsPerPage =
                Integer.parseInt(getRequestParameterOrDefault(request, "recordsPerPage", "5"));

        int rows = new PublicationService().getNumberOfPublication();

        int nOfPages = rows / recordsPerPage;

        if (rows % recordsPerPage > 0) {
            nOfPages++;
        }
        System.out.println(nOfPages);

        int start = currentPage * recordsPerPage - recordsPerPage;

        Map<String, Integer> parameters = new HashMap<>();
        parameters.put("currentPage", currentPage);
        parameters.put("recordsPerPage", recordsPerPage);
        parameters.put("numberOfPages", nOfPages);
        parameters.put("start", start);

        return parameters;
    }

    private String getRequestParameterOrDefault(HttpServletRequest request, String parameterName, String defaultValue) {
        String parameterValue = request.getParameter(parameterName);
        if (parameterValue==null || parameterValue.isEmpty()) {
            return defaultValue;
        } else {
            return parameterValue;
        }
    }
}
