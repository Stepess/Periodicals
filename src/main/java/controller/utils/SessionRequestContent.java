package controller.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionRequestContent {
    private Map<String, Object> requestAttributes ;
    private Map<String, String[]> requestParameters;
    private Map<String, Object> sessionAttributes;
    private boolean invalidateSession;
    //private Map<String, Sessi>
    private String userToLogin;
    private String userToLogout;

//    private Map<String, Object> attributesToRequest;
//    private Map<String, Object> attributesToSession;

    public SessionRequestContent() {
        requestAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
        sessionAttributes = new HashMap<>();
//        attributesToRequest = new HashMap<>();
//        attributesToSession = new HashMap<>();
        invalidateSession = false;
    }

    public void extractValues(HttpServletRequest request) {
        requestParameters = request.getParameterMap();
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String elementName = attributeNames.nextElement();
            requestAttributes.put(elementName, request.getAttribute(elementName));
        }
        HttpSession session = request.getSession();
        attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String elementName = attributeNames.nextElement();
            sessionAttributes.put(elementName, session.getAttribute(elementName));
        }

    }

    public void acceptChanges(HttpServletRequest request) {
        invalidateSession(request);//TODO mistake was here
        insertAttributes(request);
    }

    public void insertAttributes(HttpServletRequest request) {
        for (String key: requestAttributes.keySet()) {
            request.setAttribute(key, requestAttributes.get(key));
        }
        HttpSession session = request.getSession();
        for (String key: sessionAttributes.keySet()) {
            session.setAttribute(key, sessionAttributes.get(key));

        }

        String login = (String) sessionAttributes.get("login");
        if (login != null && !login.isEmpty()) {
            loginUser(request, login);
        }

    }

    private void loginUser(HttpServletRequest request, String login) {
        Map<String, Object> loginedUsers = (Map<String, Object>) request.getSession().getServletContext().getAttribute("loginedUsers");

        if (loginedUsers == null) {
            loginedUsers = new ConcurrentHashMap<>();
            loginedUsers.put(login, request.getSession());
            request.getSession().getServletContext().setAttribute("loginedUsers", loginedUsers);
        } else {
            if (loginedUsers.get(login) != null){
                ((HttpSession)loginedUsers.get(login)).invalidate();
            }
            loginedUsers.put(login, request.getSession());
        }



    }

   /* public void insertAttributes(HttpServletRequest request) {
        for (String key: attributesToRequest.keySet()) {
            request.setAttribute(key, attributesToRequest.get(key));
        }
        HttpSession session = request.getSession();
        for (String key: attributesToSession.keySet()) {
            session.setAttribute(key, attributesToSession.get(key));
        }
    }*/


    public void invalidateSession(HttpServletRequest request) {
        if (invalidateSession) {
            HttpSession session = request.getSession(false);//add false
            if (session != null) {
                Map<String, Object> map = (Map<String, Object>) session.getServletContext().getAttribute("loginedUsers");
                if (session.getAttribute("login") != null && map.containsKey(session.getAttribute("login")))
                map.remove(session.getAttribute("login"));
                sessionAttributes.clear();
                session.invalidate();
            }
        }
        invalidateSession = false;
    }


    /*public void invalidateSession(HttpServletRequest request) {
        if (invalidateSession) {
            HttpSession session = request.getSession();
            if (session != null) {
                System.out.println("before inv" + (Map<String, Object>)session.getServletContext().getAttribute("loginedUsers"));
                ((Map<String, Object>)session.getServletContext().getAttribute("loginedUsers"))
                        .remove(session.getAttribute("login"));
                System.out.println(session.getAttribute("login"));
                System.out.println("inv" + (Map<String, Object>)session.getServletContext().getAttribute("loginedUsers"));

                session.invalidate();
            }
        }
        invalidateSession = false;
    }*/

    public void addAttributeToRequest(String key, Object attribute) {
        requestAttributes.put(key, attribute);
    }

    public void addAttributeToSession(String key, Object attribute) {
        sessionAttributes.put(key, attribute);
    }


   /* public void addAttributeToSetRequest(String key, Object attribute) {
        attributesToRequest.put(key, attribute);
    }

    public void addAttributeToSetSession(String key, Object attribute) {
        attributesToSession.put(key, attribute);
    }*/

    public Object getRequestAttribute(String key) {
        return requestAttributes.get(key);
    }

    public String getRequestParameter(String key) {
        if (requestParameters.size() > 0) {
            return  requestParameters.get(key)[0];
        } else {
            return null;
        }
    }

    public String[] getRequestParameters(String key) {
        return  requestParameters.get(key);
    }

    public Object getSessionAttribute(String key) {
        return sessionAttributes.get(key);
    }

    public void setInvalidateSession(boolean invalidateSession) {
        this.invalidateSession = invalidateSession;
    }


}
