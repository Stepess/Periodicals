package controller.command;

import model.entity.DTO.PublicationDto;
import model.entity.DTO.SubscriptionDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SubscriptCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        //TODO warning NPE
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
            List<PublicationDto> list = new PublicationService().getAll();
            List<Publication> list1 = new ArrayList<>();
            Locale locale = (Locale)request.getSession().getAttribute("locale");
            for (PublicationDto dto1: list){
                list1.add(dto1.convertToInternationalizedEntity(locale));
            }
            request.setAttribute("publications", list1);
            request.setAttribute("fail", manager.getProperty("message.already.subscribed"));
            return new PagePathManager().getProperty("path.page.periodicals");
        }

        new SubscriptionService().addSubscription(subscription);
        List<SubscriptionDto> list1 = new SubscriptionService().getAllUserSubscription((String)request.getSession().getAttribute("login"));
        List<Subscription> list = new ArrayList<>();
        for (SubscriptionDto dto1: list1){
            list.add(dto1.convertToInternationalizedEntity((Locale)request.getSession().getAttribute("locale")));
        }
        request.setAttribute("subscriptions", list);
        return new PagePathManager().getProperty("path.page.subscription");
    }
}
