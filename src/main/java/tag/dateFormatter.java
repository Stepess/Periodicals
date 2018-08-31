package tag;

import model.service.resource.manager.LocalePatternManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class dateFormatter extends SimpleTagSupport {
    private LocalDate localDate;
    private Locale locale;

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public void doTag() throws JspException, IOException {
        LocalePatternManager manager = new LocalePatternManager(locale);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(manager.getProperty("pattern.date"), locale);
        getJspContext().getOut().write(localDate.format(formatter));
    }
}
