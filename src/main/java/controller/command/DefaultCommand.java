package controller.command;

import model.service.resource.manager.PagePathManager;
import model.service.resource.manager.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    private final static Logger log = LogManager.getLogger(Command.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.warn("Unsupported servlet command: " + request.getRequestURI());
        ResourceManager manager = new PagePathManager();
        return manager.getProperty("path.page.index");
    }
}
