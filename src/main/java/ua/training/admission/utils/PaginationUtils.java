package ua.training.admission.utils;

import org.apache.log4j.Logger;
import ua.training.admission.view.Messages;

import javax.servlet.http.HttpServletRequest;

public class PaginationUtils {

    /* Logger */
    private static final Logger log = Logger.getLogger(PaginationUtils.class);

    public static int getParameterValue(HttpServletRequest request, String parameterName, int defaultValue) {
        String parameter = request.getParameter(parameterName);
        int parameterValue = defaultValue;
        try {
            parameterValue = Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            log.error(Messages.NUMBER_FORMAT_EXCEPTION, e);
        }

        return parameterValue;
    }
}
