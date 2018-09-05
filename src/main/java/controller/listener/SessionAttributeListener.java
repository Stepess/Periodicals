package controller.listener;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import java.util.Arrays;
import java.util.Locale;

public class SessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        if (httpSessionBindingEvent.getName().equals("language")){
            String internalizationParameters = (String) httpSessionBindingEvent.getSession().getAttribute("language");
            httpSessionBindingEvent.getSession().setAttribute("locale",
                    new Locale(internalizationParameters.substring(0,2), internalizationParameters.substring(3,5)));
        }
    }
}
