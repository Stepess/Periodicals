package controller.command;

import model.service.SubscriptionService;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;

public class SubscriptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String login = (String) request.getSession().getAttribute("login");
        request.setAttribute("subscriptions", new SubscriptionService().getAllUserSubscription(login));
        return new PagePathManager().getProperty("path.page.subscription");
    }
}
