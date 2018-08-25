package model.service.resource.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public class RegexpManager implements ResourceManager {
    private ResourceBundle bungle;

    public RegexpManager(Locale locale) {
        bungle = ResourceBundle.getBundle("regexps", locale);
    }

    @Override
    public String getProperty(String key) {
        return bungle.getString(key);
    }
}
