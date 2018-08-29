package controller.utils;

import model.service.PublicationService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class PaginationUtil {
    public Map<String, Integer> calculatePaginationParameters(HttpServletRequest request) {
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

        int rows = new PublicationService().getNumberOfPublication();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }


        int start = currentPage * recordsPerPage - recordsPerPage;
        Map<String, Integer> parameters = new HashMap<>();
        parameters.put("currentPage", currentPage);
        parameters.put("recordsPerPage", recordsPerPage);
        parameters.put("nOfPages", nOfPages);
        parameters.put("start", start);

        return parameters;
    }
}
