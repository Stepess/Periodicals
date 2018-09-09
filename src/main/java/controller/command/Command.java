package controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Represents Command GoF pattern
 */
public interface Command {
    /**
     *
     * @param request request represents http request obtained from client
     * @return path to which will be forwarded user's request and response
     */
    String execute(HttpServletRequest request);
}
