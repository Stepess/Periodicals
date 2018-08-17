package model.service.resource.manager;

import java.util.ResourceBundle;

public class DataBaseManager implements ResourceManager {
    private ResourceBundle bungle;

    public DataBaseManager() {
        bungle = ResourceBundle.getBundle("database");
    }

    @Override
    public String getProperty(String key) {
        return bungle.getString(key);
    }
}
