package controller.command;

import controller.utils.SessionRequestContent;

public interface Command {
    String execute(SessionRequestContent content);
}
