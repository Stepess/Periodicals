package controller.command;

import controller.exception.NotEnoughMoney;
import model.entity.Subscription;
import model.service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;

import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.ResourceManager;

import java.sql.SQLException;
import java.util.Locale;

public class PayCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int subId = Integer.parseInt(request.getParameter("subId"));
        Subscription subscription = new SubscriptionService().getById(subId);

        ResourceManager manager = new MessageManager((Locale)request.getSession().getAttribute("locale"));

        if (subscription.isPaid()) {
            request.setAttribute("fail", manager.getProperty("message.already.paid"));
            request.setAttribute("subscriptions", new SubscriptionService().getAllUserSubscription((String)request.getSession().getAttribute("login")));
            return new PagePathManager().getProperty("path.page.subscription");
        }

        try {
            new SubscriptionService().pay((String)request.getSession().getAttribute("login"),
                    subscription);
        }
        catch (NotEnoughMoney e) {
            request.setAttribute("fail", manager.getProperty("message.not.enough.money"));
            request.setAttribute("subscriptions", new SubscriptionService().getAllUserSubscription((String)request.getSession().getAttribute("login")));
            return new PagePathManager().getProperty("path.page.subscription");
        }
        catch (SQLException e) {
            request.setAttribute("fail", manager.getProperty("message.pay.error"));
            request.setAttribute("subscriptions", new SubscriptionService().getAllUserSubscription((String)request.getSession().getAttribute("login")));
            return new PagePathManager().getProperty("path.page.subscription");

        }

        request.setAttribute("success", manager.getProperty("message.paid"));
        request.setAttribute("subscriptions", new SubscriptionService().getAllUserSubscription((String)request.getSession().getAttribute("login")));
        return new PagePathManager().getProperty("path.page.subscription");



    }
}
