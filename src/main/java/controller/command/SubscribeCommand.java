package controller.command;

import controller.utils.DataValidationUtil;
import model.entity.DTO.PublicationDto;
import model.entity.DTO.SubscriptionDto;
import model.entity.Payment;
import model.entity.Publication;
import model.entity.Subscription;
import model.service.PublicationService;
import model.service.SubscriptionService;
import model.service.UserService;
import model.service.builders.SubscriptionBuilder;
import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.RegexpManager;
import model.service.resource.manager.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SubscribeCommand implements Command{
    //TODO static final green string

    @Override
    public String execute(HttpServletRequest request) {
        LocalDate from = LocalDate.now();
        Locale locale = (Locale)request.getSession().getAttribute("locale");

        new DataValidationUtil(locale).isDataValid(request, "months",
                new RegexpManager(locale).getProperty("subscription.months"));

        if (request.getAttribute("wrongmonths") != null) {
            return new PagePathManager().getProperty("path.command.user.catalog");
        }

        int months = Integer.parseInt(request.getParameter("months"));
        ResourceManager manager = new MessageManager((Locale)request.getSession().getAttribute("locale"));
        PublicationDto dto = new PublicationService().getById(Integer.parseInt(request.getParameter("pubId")));
        String login = (String) request.getSession().getAttribute("login");

        Subscription subscription = new SubscriptionBuilder()
                .buildStartDate(from)
                .buildPublication(dto.convertToInternationalizedEntity(locale))
                .buildPayment(new Payment())
                .buildEndDate(from.plusMonths(months))
                .buildState(Subscription.StateEnum.UNPAID)
                .buildOwnerId(new UserService().getUserId(login))
                .build();

        subscription.calculatePrice(months);

        if (! new SubscriptionService().isUserSubscriptionUnique(login, Integer.parseInt(request.getParameter("pubId")))) {
            request.setAttribute("fail", manager.getProperty("message.already.subscribed"));
            return new PagePathManager().getProperty("path.command.user.catalog");
        }

        new SubscriptionService().addSubscription(subscription);
        return new PagePathManager().getProperty("path.command.user.subscription")+"?state=UNPAID";
    }
}
