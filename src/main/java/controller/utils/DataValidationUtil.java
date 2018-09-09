package controller.utils;

import model.service.resource.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import model.service.resource.manager.ResourceManager;

/**
 * Validates information obtained from user's {@code request}
 */
public class DataValidationUtil {

    private ResourceManager manager;

    public DataValidationUtil(Locale locale) {
        manager = new MessageManager(locale);
    }

    /**
     * Checks if parameter in user's request is valid: presents and matches it via regex.
     * In case that if parameter isn't valid sets message about incorrect data in request named "wrong" + parameter name
     * @param request represents http request obtained from client
     * @param parameter parameter name
     * @param regex regex to validate parameter
     */
    public void isDataValid(HttpServletRequest request, String parameter, String regex) {
        String data = request.getParameter(parameter);
        if (data == null || !data.matches(regex)) {
            request.setAttribute("wrong" + parameter,
                    manager.getProperty("message.wrong." + parameter));
        }
    }

    /**
     * Returns <tt>true</tt> if request contains no parameters except "language"
     * @param request represents http request obtained from client
     * @return <tt>true</tt> if request contains no parameters except "language"
     */
    public boolean isEmptyRequest(HttpServletRequest request) {
        if (request.getParameterMap().keySet().contains("language")) {
            return true;
        }
        return !request.getParameterNames().hasMoreElements();
    }
}
