package controller.command;

import model.service.SubscriptionService;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;

public class SubscriptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("subscriptions", new SubscriptionService().getAllUserSubscription(
                (String) request.getSession().getAttribute("login")));
        return new PagePathManager().getProperty("path.page.subscription");
    }
}
