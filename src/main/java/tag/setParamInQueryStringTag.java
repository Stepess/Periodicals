package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class setParamInQueryStringTag extends SimpleTagSupport {

    private String query;
    private String paramName;
    private String paramValue;

    public setParamInQueryStringTag() {
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    @Override
    public void doTag() throws JspException, IOException {
        try {
            if (query==null) {
                query = "";
            }
            if (query.isEmpty()){
                query = "?"+paramName+"="+paramValue;
            } else {
                if (query.contains(paramName)) {
                    query = query + "&";
                    query = query.replaceAll(paramName+"=.*&", paramName+"="+paramValue+"&");
                    query = query.substring(0, query.length()-1);
                } else {
                    query = query + "&"+paramName+"="+paramValue;
                }
            }
            System.out.println(query);
            getJspContext().getOut().write(query);
        } catch (Exception e) {
            e.printStackTrace();
            // stop page from loading further by throwing SkipPageException
            /*throw new SkipPageException("Exception in formatting " + number
                    + " with format " + format);*/
        }
    }
}






