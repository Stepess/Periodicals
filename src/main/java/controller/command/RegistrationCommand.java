package controller.command;

import controller.utils.DataValidationUtil;
import model.entity.User;
import model.exception.NotUniqueEmailException;
import model.exception.NotUniqueLoginException;
import model.service.UserService;
import model.service.builders.UserBuilder;
import model.service.resource.manager.MessageManager;
import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.RegexpManager;
import model.service.resource.manager.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Locale;

public class RegistrationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        Locale locale = (Locale)request.getSession().getAttribute("locale");
        ResourceManager regexManager = new RegexpManager(locale);

        DataValidationUtil validationUtil = new DataValidationUtil(locale);

        try{
            new UserService().checkDataUnique(request.getParameter("login"),
                    request.getParameter("email"));
        } catch (NotUniqueLoginException ex) {
            request.setAttribute("wronglogin", new MessageManager(locale)
                    .getProperty("message.not.unique.login"));
        } catch (NotUniqueEmailException ex) {
            request.setAttribute("wrongemail", new MessageManager(locale)
            .getProperty("message.not.unique.email"));
        }

        validationUtil.isDataValid(request, "login", regexManager.getProperty("user.login"));
        validationUtil.isDataValid(request, "password", regexManager.getProperty("user.password"));
        validationUtil.isDataValid(request, "passwordDuplicate", request.getParameter("password"));
        validationUtil.isDataValid(request, "email", regexManager.getProperty("user.email"));
        validationUtil.isDataValid(request, "lastName", regexManager.getProperty("user.last.name"));
        validationUtil.isDataValid(request, "firstName", regexManager.getProperty("user.first.name"));
        validationUtil.isDataValid(request, "country", regexManager.getProperty("user.country"));
        validationUtil.isDataValid(request, "city", regexManager.getProperty("user.city"));
        validationUtil.isDataValid(request, "street", regexManager.getProperty("user.street"));


        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String elementName = attributeNames.nextElement();
            if (elementName.contains("wrong")) {
                return new PagePathManager().getProperty("path.page.registration");
            }
        }

        StringBuffer sb = new StringBuffer(request.getParameter("country"))
                .append(", ")
                .append(request.getParameter("city"))
                .append(", ")
                .append(request.getParameter("street"))
                .append(", ")
                .append(request.getParameter("building"));


        User user = new UserBuilder().buildLogin(request.getParameter("login"))
                .buildPassword(request.getParameter("password"))
                .buildEmail(request.getParameter("email"))
                .buildRole(User.RoleEnum.USER)
                .buildFirstName(request.getParameter("firstName"))
                .buildLastName(request.getParameter("lastName"))
                .buildAddress(sb.toString())
                .buildAccount(BigDecimal.ZERO)
                .build();

        new UserService().setInDb(user);

        request.getSession().setAttribute("login", user.getLogin());
        request.getSession().setAttribute("role", User.RoleEnum.USER.getValue());
        request.getSession().getServletContext().setAttribute(user.getLogin(), request.getSession());

        return  "redirect:/user" + new PagePathManager().getProperty("path.page.main");
    }


}
