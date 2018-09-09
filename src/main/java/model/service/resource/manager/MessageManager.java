package model.service.resource.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager implements ResourceManager {
    private ResourceBundle bungle;

    public MessageManager(Locale locale) {
        bungle = ResourceBundle.getBundle("messages", locale);
    }

    @Override
    public String getProperty(String key) {
        return bungle.getString(key);
    }
}
