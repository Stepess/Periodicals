package controller.utils;


import model.entity.User;

public class SecurityUtils {

    public boolean isSecurityPage(String urlPattern) {
        for (User.RoleEnum role : User.RoleEnum.values()) {
            if (urlPattern.contains(role.getValue())){
                return true;
            }
        }
        return false;
    }

    public boolean hasPermission(String urlPattern, String role) {
        return urlPattern.contains(role);
    }
}
