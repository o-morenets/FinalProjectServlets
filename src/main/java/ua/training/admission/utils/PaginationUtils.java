package ua.training.admission.utils;

import org.apache.log4j.Logger;
import ua.training.admission.view.Messages;

import javax.servlet.http.HttpServletRequest;

/**
 * Pagination Utils
 *
 * @author Oleksii Morenets
 */
public class PaginationUtils {

    /* Logger */
    private static final Logger log = Logger.getLogger(PaginationUtils.class);

    /**
     * Get parameter value from http request
     *
     * @param request       http request
     * @param parameterName parameter name
     * @param defaultValue  default value
     * @return parameter value from http request
     */
    public static int getParameterValue(HttpServletRequest request, String parameterName, int defaultValue) {
        String parameter = request.getParameter(parameterName);
        int parameterValue = defaultValue;
        try {
            parameterValue = Integer.parseInt(parameter);
        } catch (NumberFormatException ignored) {
        }

        return parameterValue;
    }
}
