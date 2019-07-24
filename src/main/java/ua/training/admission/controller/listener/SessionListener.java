package ua.training.admission.controller.listener;

import ua.training.admission.view.Attributes;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute(Attributes.LOGGED_USERS);
        String userName = (String) httpSessionEvent.getSession().getAttribute(Attributes.USER_NAME);
        loggedUsers.remove(userName);
        httpSessionEvent.getSession().setAttribute(Attributes.LOGGED_USERS, loggedUsers);
    }
}
