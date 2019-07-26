package ua.training.admission.controller.command;

import org.apache.log4j.Logger;
import ua.training.admission.model.service.UserService;
import ua.training.admission.view.Attributes;
import ua.training.admission.view.Parameters;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateSpecialityCommand extends CommandWrapper {

    private static final Logger LOG = Logger.getLogger(UpdateSpecialityCommand.class);
    private UserService userService = UserService.getInstance();

    @Override
    String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String paramUserId = request.getParameter(Parameters.USER_ID);
        final String paramSpecId = request.getParameter(Parameters.SPEC_RADIOS);
        Long userId = Long.valueOf(paramUserId);
        Long specId = Long.valueOf(paramSpecId);
        userService.updateSpeciality(userId, specId);
        response.sendRedirect(request.getContextPath() + Paths.USERS_ + userId + Paths.GRADES);

        return Paths.REDIRECTED;
    }
}
