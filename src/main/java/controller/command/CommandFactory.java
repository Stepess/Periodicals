package controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class CommandFactory {
    private final static Logger log = LogManager.getLogger(CommandFactory.class);

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
        catch (IllegalArgumentException ex) {
            log.warn("No such command: " + commandName);
        }
        return command;
    }
}