package controller.command;

import controller.utils.SessionRequestContent;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request);
}
