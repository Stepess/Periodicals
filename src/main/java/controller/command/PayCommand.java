package controller.command;

import controller.exception.NotEnoughMoney;
import model.entity.DTO.SubscriptionDto;
import model.entity.Subscription;
import model.entity.User;
import model.service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;

import model.service.UserService;
import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.ResourceManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PayCommand implements Command {
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
        catch (NotEnoughMoney e) {
            request.setAttribute("fail", manager.getProperty("message.not.enough.money"));
            return new PagePathManager().getProperty("path.command.user.subscription");
        }
        catch (SQLException e) {
            request.setAttribute("fail", manager.getProperty("message.pay.error"));
            return new PagePathManager().getProperty("path.command.user.subscription");
        }

        request.setAttribute("success", manager.getProperty("message.paid"));
        return new PagePathManager().getProperty("path.command.user.subscription");
    }
}
