package controller.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Calculates pagination parameters
 */
public class PaginationUtil {

    /**
     * Calculates pagination parameters to show information paginated
     * @param request represents http request obtained from client
     * @param rows number of rows to show
     * @return {@code HashMap} that contains pagination parameters
     */
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
