package controller.command;

import model.entity.DTO.PublicationDto;
import model.entity.Publication;
import model.service.PublicationService;
import model.service.resource.manager.PagePathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        /*Map<PublicationDto, Integer> statistics = new PublicationService().getStatistics();
        List<Publication> publications = statistics.keySet().stream()
                .map(dto -> dto.convertToInternationalizedEntity((Locale)request.getSession().getAttribute("locale")))
                .collect(Collectors.toList());
        request.setAttribute("publications", statistics.keySet()
                .stream()
                .collect(Collectors.toMap(statistics.keySet().stream().map(dto -> dto.convertToInternationalizedEntity(
                        )
                        , statistics.values().stream().map(quantity -> quantity.intValue()))));*/

        request.setAttribute("statistics", new PublicationService().getStatistics().entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey()
                                .convertToInternationalizedEntity((Locale)request.getSession().getAttribute("locale")).getTitle(),
                        Map.Entry::getValue)));


        return new PagePathManager().getProperty("path.page.statistics");
    }
}
