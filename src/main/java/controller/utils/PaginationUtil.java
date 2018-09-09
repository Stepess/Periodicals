package controller.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class PaginationUtil {
    public Map<String, Integer> calculatePaginationParameters(HttpServletRequest request, int rows) {
        RequestContentUtil requestContentUtil = new RequestContentUtil();
        int currentPage =
                Integer.parseInt(requestContentUtil.getRequestParameterOrDefault(request, "currentPage", "1"));

        int recordsPerPage =
                Integer.parseInt(requestContentUtil.getRequestParameterOrDefault(request, "recordsPerPage", "5"));

        int numberOfPages = rows / recordsPerPage;

        if (rows % recordsPerPage > 0) {
            numberOfPages++;
        }

        if (currentPage > numberOfPages) {
            currentPage = numberOfPages;
        }

        int start = currentPage * recordsPerPage - recordsPerPage;

        Map<String, Integer> parameters = new HashMap<>();
        parameters.put("currentPage", currentPage);
        parameters.put("recordsPerPage", recordsPerPage);
        parameters.put("numberOfPages", numberOfPages);
        parameters.put("start", start);

        return parameters;
    }
}
