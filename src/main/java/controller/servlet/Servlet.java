package controller.servlet;


import controller.command.Command;
import controller.command.CommandFactory;
import model.service.resource.manager.MessageManager;
import sun.util.locale.LocaleUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

public class Servlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        //getServletContext().setAttribute("loginedUsers", new ConcurrentHashMap<>());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("REQUEST!!!!!!!");
        System.out.println("lang class" + request.getSession().getAttribute("language").getClass());
        //System.out.println(new MessageManager((Locale)request.getSession().getAttribute("language")).getProperty("message.paid"));
        // System.out.println(new Locale.Builder().setLanguageTag((String)request.getSession().getAttribute("language")));
        System.out.println(new Locale("en", "US"));
        System.out.println("locale from request" + request.getLocale());

        CommandFactory commandFactory = new CommandFactory();
        Command command = commandFactory.getCommandFromRequest(request);
        String page = command.execute(request);
        System.out.println(page);
        if (page.contains("redirect:")) {
            System.out.println(page);
            response.sendRedirect(request.getContextPath() + page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }

    }
}
