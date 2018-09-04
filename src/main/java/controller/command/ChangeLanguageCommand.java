package controller.command;

import javax.servlet.http.HttpServletRequest;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("change lang" + request.getParameter("command")+"?"+request.getParameter("query"));
        System.out.println(request.getRequestURI());
        System.out.println(request.getParameter("command1"));
        return request.getParameter("command")+"?"+request.getParameter("query");
    }
}
