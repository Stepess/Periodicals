package tag;

import model.service.resource.manager.LocalePatternManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;

public class CurrencyFormatterTag extends SimpleTagSupport {
    private BigDecimal money;
    private Locale locale;

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public void doTag() throws JspException, IOException {
        LocalePatternManager manager = new LocalePatternManager(locale);
        getJspContext().getOut().write(manager.getProperty("pattern.currency.sign") +
                money.multiply(new BigDecimal(manager.getProperty("pattern.currency.course")))
                        .setScale(2, BigDecimal.ROUND_DOWN));
    }
}
