package controller.command;

import model.entity.User;
import model.service.PublicationService;
import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

public class ShowReportCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<User> report = new PublicationService().getReport(Integer.parseInt(request.getParameter("pubId")));

        if (report.isEmpty()){
            request.setAttribute("fail", new MessageManager((Locale) request.getSession().getAttribute("locale")).
                    getProperty("message.nobody.subscribe"));
        } else {
            request.setAttribute("report", report);
        }

        return new PagePathManager().getProperty("path.page.report");
    }
}
