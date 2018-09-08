package controller.command;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class CommandFactory {
    public Command getCommandFromRequest(HttpServletRequest request) {
        Command command = new DefaultCommand();

        String commandName = request.getRequestURI();
        commandName = commandName.replaceAll(".*/user/|.*/admin/|.*/guest/", "");

        if (commandName == null || commandName.isEmpty()) {
            return command;
        }
        try {
            CommandEnum commandEnum = CommandEnum.valueOf(commandName.toUpperCase());
            command = commandEnum.getCommand();
        }
        catch (IllegalArgumentException ex) {//TODO add logger
            //request.setAttribute("wrongAction", commandName);
        }
        return command;
    }
}