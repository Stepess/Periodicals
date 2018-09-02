package controller.command;

import model.service.SubscriptionService;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.stream.Collectors;

public class PaymentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("subscriptions",
                new SubscriptionService().getAllUserSubscription((String)request.getSession().getAttribute("login"), "PAID")
                        .stream()
                        .map(dto -> dto.convertToInternationalizedEntity((Locale)request.getSession().getAttribute("locale")))
                        .collect(Collectors.toList()));
        return new PagePathManager().getProperty("path.page.payment");
    }
}
