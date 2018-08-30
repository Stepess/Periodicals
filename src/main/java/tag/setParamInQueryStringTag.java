package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class setParamInQueryStringTag extends SimpleTagSupport {

    private String query;

    public setParamInQueryStringTag() {
    }

    public void setQuery(String query) {
        this.query = query;
    }


    //TODO refactor

    @Override
    public void doTag() throws JspException, IOException {
        try {
            if (query==null || query.isEmpty()) {
                query = "?";
            } else {
                query = query.replaceAll("recordsPerPage=.*&", "");
                query = query.replaceAll("currentPage=.*&", "");
                query = query.replaceAll("&currentPage=.*", "");
                query = query.replaceAll("currentPage=.*", "");
                query = query.replaceAll("&recordsPerPage=.*", "");
                query = query + "&";
            }
            System.out.println(query);
            getJspContext().getOut().write(query);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}



/*@Override
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

        }
                }*/


