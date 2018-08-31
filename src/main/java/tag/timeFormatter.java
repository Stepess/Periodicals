package tag;

import model.service.resource.manager.LocalePatternManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class timeFormatter extends SimpleTagSupport {
    private LocalTime localTime;
    private Locale locale;

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public void doTag() throws JspException, IOException {
        LocalePatternManager manager = new LocalePatternManager(locale);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(manager.getProperty("pattern.time"), locale);
        getJspContext().getOut().write(localTime.format(formatter));
    }
}
