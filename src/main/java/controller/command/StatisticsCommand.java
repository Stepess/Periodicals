package controller.command;

import model.service.PublicationService;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("statistics", new PublicationService().getStatistics().entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey()
                        .convertToInternationalizedEntity((Locale) request.getSession().getAttribute("locale"))
                        .getTitle(), Map.Entry::getValue)));
        return new PagePathManager().getProperty("path.page.statistics");
    }
}
