package controller.utils;

import model.service.resource.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import model.service.resource.manager.ResourceManager;

public class DataValidationUtil {

    private ResourceManager manager;

    public DataValidationUtil(Locale locale) {
        manager = new MessageManager(locale);
    }

    public void isDataValid(HttpServletRequest request, String parameter, String regex) {
        String data = request.getParameter(parameter);
        if (data== null || !data.matches(regex)) {
            request.setAttribute("wrong"+parameter,
                    manager.getProperty("message.wrong."+parameter));
        }
    }

    public boolean isEmptyRequest(HttpServletRequest request) {
        if (request.getParameterMap().keySet().contains("language")) {
            return true;
        }

        return !request.getParameterNames().hasMoreElements();
    }
}
