package tag;

import model.service.resource.manager.LocalePatternManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class dateTimeFormatter extends SimpleTagSupport {
    private LocalDateTime localDateTime;
    private Locale locale;

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public void doTag() throws JspException, IOException {
        LocalePatternManager manager = new LocalePatternManager(locale);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(manager.getProperty("pattern.time"), locale);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(manager.getProperty("pattern.date"), locale);
        getJspContext().getOut().write(localDateTime.toLocalDate().format(dateFormatter) + " " +
                localDateTime.toLocalTime().format(timeFormatter));
    }
}
