package controller.command;

import controller.exception.NotEnoughMoney;
import model.entity.DTO.SubscriptionDto;
import model.entity.Subscription;
import model.service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;

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
            List<SubscriptionDto> list1 = new SubscriptionService().getAllUserSubscription((String)request.getSession().getAttribute("login"));
            List<Subscription> list = new ArrayList<>();
            for (SubscriptionDto dto: list1){
                list.add(dto.convertToInternationalizedEntity((Locale)request.getSession().getAttribute("locale")));
            }
            request.setAttribute("subscriptions", list);
            return new PagePathManager().getProperty("path.page.subscription");
        }

        try {
            new SubscriptionService().pay((String)request.getSession().getAttribute("login"),
                    subscription);
        }
        catch (NotEnoughMoney e) {
            request.setAttribute("fail", manager.getProperty("message.not.enough.money"));
            List<SubscriptionDto> list1 = new SubscriptionService().getAllUserSubscription((String)request.getSession().getAttribute("login"));
            List<Subscription> list = new ArrayList<>();
            for (SubscriptionDto dto: list1){
                list.add(dto.convertToInternationalizedEntity((Locale)request.getSession().getAttribute("locale")));
            }
            request.setAttribute("subscriptions", list);
            return new PagePathManager().getProperty("path.page.subscription");
        }
        catch (SQLException e) {
            request.setAttribute("fail", manager.getProperty("message.pay.error"));
            List<SubscriptionDto> list1 = new SubscriptionService().getAllUserSubscription((String)request.getSession().getAttribute("login"));
            List<Subscription> list = new ArrayList<>();
            for (SubscriptionDto dto: list1){
                list.add(dto.convertToInternationalizedEntity((Locale)request.getSession().getAttribute("locale")));
            }
            request.setAttribute("subscriptions", list);
            return new PagePathManager().getProperty("path.page.subscription");

        }

        request.setAttribute("success", manager.getProperty("message.paid"));
        List<SubscriptionDto> list1 = new SubscriptionService().getAllUserSubscription((String)request.getSession().getAttribute("login"));
        List<Subscription> list = new ArrayList<>();
        for (SubscriptionDto dto: list1){
            list.add(dto.convertToInternationalizedEntity((Locale)request.getSession().getAttribute("locale")));
        }
        request.setAttribute("subscriptions", list);
        return new PagePathManager().getProperty("path.page.subscription");



    }
}
