package model.service.resource.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public class DBFieldsManager implements ResourceManager {
    private ResourceBundle bungle;

    public DBFieldsManager(Locale locale) {
        bungle = ResourceBundle.getBundle("dbFields", locale);
    }

    @Override
    public String getProperty(String key) {
        return bungle.getString(key);
    }
}
