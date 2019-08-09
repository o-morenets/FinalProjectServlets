package ua.training.admission.controller.listener;

import ua.training.admission.security.SecurityUtils;
import ua.training.admission.view.Attributes;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

/**
 * Session listener
 *
 * @author Oleksii Morenets
 */
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setMaxInactiveInterval(30 * 60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        SecurityUtils.removeLoggedUser(httpSessionEvent.getSession());
    }
}
