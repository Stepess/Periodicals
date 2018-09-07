package controller.command;

import model.service.SubscriptionService;
import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class DeleteSubscriptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.valueOf(request.getParameter("subId"));
        if(! new SubscriptionService().deleteUserSubscription(id)){
            throw new RuntimeException(
                    new MessageManager((Locale)request.getSession().getAttribute("locale"))
                            .getProperty("message.changes.not.accepted"));
        }
        return new PagePathManager().getProperty("path.command.user.subscription")+"?state=UNPAID";
    }
}
