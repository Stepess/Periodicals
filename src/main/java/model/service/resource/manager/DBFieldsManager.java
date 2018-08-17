package model.service.resource.manager;

import java.util.ResourceBundle;

public class DBFieldsManager implements ResourceManager {
    private ResourceBundle bungle;

    public DBFieldsManager() {
        bungle = ResourceBundle.getBundle("dbFields");
    }

    @Override
    public String getProperty(String key) {
        return bungle.getString(key);
    }
}
