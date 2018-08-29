package model.service.resource.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalePatternManager implements ResourceManager {
    private ResourceBundle bungle;

    public LocalePatternManager(Locale locale) {
        bungle = ResourceBundle.getBundle("localePattern", locale);
    }

    @Override
    public String getProperty(String key) {
        return bungle.getString(key);
    }
}
