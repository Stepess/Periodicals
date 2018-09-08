package controller.utils;


import model.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecurityUtils {
    private static final Map<String, List<String>> permittedCommandMap = new HashMap<>();

    static {
        init();
    }

    private static void init(){
        List<String> adminCommand = new ArrayList<>();
        adminCommand.add("addPublication");
        adminCommand.add("editPublication");
        adminCommand.add("statistics");
        adminCommand.add("search");
        adminCommand.add("catalog");
        adminCommand.add("changeLanguage");
        adminCommand.add("default");
        adminCommand.add("logout");
        adminCommand.add("showReport");
        adminCommand.add("deletePublication");

        List<String> userCommand = new ArrayList<>();
        userCommand.add("subscribe");
        userCommand.add("subscriptions");
        userCommand.add("search");
        userCommand.add("catalog");
        userCommand.add("logout");
        userCommand.add("replenish");
        userCommand.add("profile");
        userCommand.add("pay");
        userCommand.add("payments");
        userCommand.add("deleteSubscription");
        userCommand.add("changeLanguage");
        userCommand.add("default");

        List<String> guestCommand = new ArrayList<>();
        guestCommand.add("login");
        guestCommand.add("registration");
        guestCommand.add("catalog");
        guestCommand.add("search");
        guestCommand.add("changeLanguage");
        guestCommand.add("default");

        permittedCommandMap.put("admin", adminCommand);
        permittedCommandMap.put("user", userCommand);
        permittedCommandMap.put("guest", guestCommand);
    }

    public boolean isSecurityPage(String urlPattern) {
        for (User.RoleEnum role : User.RoleEnum.values()) {
            if (urlPattern.contains(role.getValue())){
                return true;
            }
        }
        return false;
    }

    public boolean hasPermission(String urlPattern, String role) {
        if (urlPattern.contains(role)) {
            String command = urlPattern.replaceFirst(".*/", "");
            return permittedCommandMap.get(role).contains(command);
        }
        return false;
    }
}
