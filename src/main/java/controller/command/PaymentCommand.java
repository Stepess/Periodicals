package controller.command;

import model.entity.Subscription;
import model.service.SubscriptionService;
import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class PaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<Subscription> subscriptions = new SubscriptionService().getAllUserSubscription(
                (String)request.getSession().getAttribute("login"), "PAID")
                .stream()
                .map(dto -> dto.convertToInternationalizedEntity((Locale)request.getSession().getAttribute("locale")))
                .collect(Collectors.toList());

        if (subscriptions.isEmpty()) {
            request.setAttribute("fail", new MessageManager((Locale)request.getSession().getAttribute("locale"))
                    .getProperty("message.not.payments"));
        } else {
            request.setAttribute("subscriptions", subscriptions);
        }

        return new PagePathManager().getProperty("path.page.payment");
    }
}
