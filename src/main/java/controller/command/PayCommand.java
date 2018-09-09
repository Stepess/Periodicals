package controller.command;

import model.exception.NotEnoughMoneyException;
import model.entity.Payment;
import model.entity.Subscription;
import model.entity.User;
import model.service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;

import model.service.UserService;
import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Locale;

public class PayCommand implements Command {
    private final static Logger log = LogManager.getLogger(Payment.class);

    @Override
    public String execute(HttpServletRequest request) {
        int subId = Integer.parseInt(request.getParameter("subId"));
        Subscription subscription = new SubscriptionService().getById(subId);
        ResourceManager manager = new MessageManager((Locale)request.getSession().getAttribute("locale"));

        if (subscription.isPaid()) {
            request.setAttribute("fail", manager.getProperty("message.already.paid"));
            return new PagePathManager().getProperty("path.command.user.subscription");
        }

        User user = new UserService().getUserByLogin((String)request.getSession().getAttribute("login"));

        try {
            new SubscriptionService().paySubscription(user, subscription);
        }
        catch (NotEnoughMoneyException e) {
            request.setAttribute("fail", manager.getProperty("message.not.enough.money"));
            return new PagePathManager().getProperty("path.command.user.subscription");
        }
        catch (RuntimeException e) {
            log.error("Attempt to pay has failed, payment id " + subscription.getPayment().getId() + "user " +
                    request.getSession().getAttribute("login"));
            request.setAttribute("fail", manager.getProperty("message.pay.error"));
            return new PagePathManager().getProperty("path.command.user.subscription");
        }

        request.setAttribute("success", manager.getProperty("message.paid"));
        return new PagePathManager().getProperty("path.command.user.subscription");
    }
}
