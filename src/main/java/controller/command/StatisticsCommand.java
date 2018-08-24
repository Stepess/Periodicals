package controller.command;

import model.service.PublicationService;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;

public class StatisticsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("statistics", new PublicationService().getStatistics());
        return new PagePathManager().getProperty("path.page.statistics");
    }
}
