package controller.command;

import controller.utils.RequestContentUtil;
import model.entity.Subscription;
import model.service.SubscriptionService;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.stream.Collectors;

public class SubscriptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String state = new RequestContentUtil().getRequestParameterOrDefault(request, "state", "PAID");
        request.setAttribute("states", Subscription.StateEnum.values());
        request.setAttribute("chosen_state", state);
        request.setAttribute("subscriptions",
                new SubscriptionService().getAllUserSubscription((String)request.getSession().getAttribute("login"), state)
                        .stream()
                        .map(dto -> dto.convertToInternationalizedEntity((Locale)request.getSession().getAttribute("locale")))
                        .collect(Collectors.toList()));
        return new PagePathManager().getProperty("path.page.subscription");
    }
}
