package controller.command;

import model.entity.DTO.SubscriptionDto;
import model.entity.Subscription;
import model.service.SubscriptionService;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SubscriptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<SubscriptionDto> list1 = new SubscriptionService().getAllUserSubscription((String)request.getSession().getAttribute("login"));
        List<Subscription> list = new ArrayList<>();
        for (SubscriptionDto dto: list1){
            list.add(dto.convertToInternationalizedEntity((Locale)request.getSession().getAttribute("locale")));
        }
        System.out.println(list.toString());
        request.setAttribute("subscriptions", list);
        return new PagePathManager().getProperty("path.page.subscription");
    }
}
