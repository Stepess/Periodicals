package controller.command;

import model.service.PublicationService;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;

public class ShowReportCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("report", new PublicationService().getReport(Integer.parseInt(request.getParameter("pubId"))));
        return new PagePathManager().getProperty("path.page.report");
    }
}
