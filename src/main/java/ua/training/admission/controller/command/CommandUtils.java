package ua.training.admission.controller.command;

import ua.training.admission.view.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * Helper class for command processing
 *
 * @author Oleksii Morenets
 */
public class CommandUtils {

    /**
     * Detect GET or POST method and return appropriate command
     *
     * @param request http request
     * @return {GET|POST}:{URI}
     */
    public static String getCommandKey(HttpServletRequest request) {
        String method = request.getMethod().toUpperCase();
        String path = request.getPathInfo().replaceAll("/\\d+", "");

        return method + Constants.COLON + path;
    }

    /**
     * Extracts user Id from request path
     *
     * @param request http request
     * @return number that represents user Id
     */
    static Long extractUserIdFromPath(HttpServletRequest request) {
        return Long.valueOf(request.getPathInfo().replaceAll("\\D+", ""));
    }
}
