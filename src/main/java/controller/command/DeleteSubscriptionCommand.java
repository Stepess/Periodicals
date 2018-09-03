package controller.command;

import model.service.SubscriptionService;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;

public class DeleteSubscriptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.valueOf(request.getParameter("subId"));
        new SubscriptionService().deleteUserSubscription(id);
        return new PagePathManager().getProperty("path.command.user.subscription");
    }
}
