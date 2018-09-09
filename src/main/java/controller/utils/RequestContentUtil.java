package controller.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestContentUtil {
    public String getRequestParameterOrDefault(HttpServletRequest request, String parameterName, String defaultValue) {
        String parameterValue = request.getParameter(parameterName);
        if (parameterValue == null || parameterValue.isEmpty()) {
            return defaultValue;
        } else {
            return parameterValue;
        }
    }
}
