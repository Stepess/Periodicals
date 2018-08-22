package controller.command;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    public Command getCommandFromRequest(HttpServletRequest request) {
        Command command = new DefaultCommand();

        /*String path = request
       .getRequestURI();
        String temp[] = path.split("/");
        String commandName = null;
        if (temp.length>1){
            commandName = temp[temp.length-1];
        }*/

        String commandName = request.getRequestURI();
        commandName = commandName.replaceAll(".*/app/", "");

        if (commandName == null || commandName.isEmpty()) {
            return command;
        }
        try {
            CommandEnum commandEnum = CommandEnum.valueOf(commandName.toUpperCase());
            command = commandEnum.getCommand();
        }
        catch (IllegalArgumentException ex) {//TODO add logger
            request.setAttribute("wrongAction", commandName);
        }
        return command;
    }
}