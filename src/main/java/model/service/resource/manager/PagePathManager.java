package model.service.resource.manager;

import java.util.ResourceBundle;

public class PagePathManager implements ResourceManager {
    private ResourceBundle bungle;

    public PagePathManager() {
        bungle = ResourceBundle.getBundle("path");
    }

    @Override
    public String getProperty(String key) {
        return bungle.getString(key);
    }
}
