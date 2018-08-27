package controller.command;

import model.entity.DTO.PublicationDto;
import model.entity.Publication;
import model.entity.Subscription;
import model.service.PublicationService;
import model.service.SubscriptionService;
import model.service.UserService;
import model.service.builders.SubscriptionBuilder;
import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

public class SubscriptCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        LocalDate from = LocalDate.parse(request.getParameter("from"));
        int months = Integer.parseInt(request.getParameter("months"));
        ResourceManager manager = new MessageManager((Locale)request.getSession().getAttribute("locale"));
        PublicationDto dto = new PublicationService().getById(Integer.parseInt(request.getParameter("pubId")));
        String login = (String) request.getSession().getAttribute("login");

        Subscription subscription = new SubscriptionBuilder()
                .buildStartDate(from)
                .buildTotal(dto.getPrice().multiply(BigDecimal.valueOf(months)))
                .buildPublication(dto.convertToInternationalizedEntity((Locale)request.getSession().getAttribute("locale")))
                .buildEndDate(from.plusMonths(months))
                .buildState(Subscription.StateEnum.UNPAID)
                .buildOwnerId(new UserService().getUserId(login))
                .build();

        if (! new SubscriptionService().isUserSubscriptionUnique(login, Integer.parseInt(request.getParameter("pubId")))) {
            request.setAttribute("fail", manager.getProperty("message.already.subscribed"));
            request.setAttribute("publications", new PublicationService().getAll());
            return new PagePathManager().getProperty("path.page.periodicals");
        }

        new SubscriptionService().set(subscription);
        request.setAttribute("subscriptions", new SubscriptionService().getAllUserSubscription(login));
        return new PagePathManager().getProperty("path.page.subscription");
    }
}
