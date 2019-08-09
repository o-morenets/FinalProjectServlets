package ua.training.admission.controller.command.roles.admin;

import org.apache.log4j.Logger;
import ua.training.admission.controller.command.CommandWrapper;
import ua.training.admission.controller.command.roles.user.UpdateSpecialityCommand;
import ua.training.admission.model.service.SubjectGradeService;
import ua.training.admission.view.Parameters;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UpdateGrades Command
 *
 * @author Oleksii Morenets
 */
public class UpdateGradesCommand extends CommandWrapper {

    /* Logger */
    private static final Logger log = Logger.getLogger(UpdateSpecialityCommand.class);

    private SubjectGradeService subjectGradeService = SubjectGradeService.getInstance();

    @Override
    protected String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long userId = Long.valueOf(request.getParameter(Parameters.USER_ID));
        subjectGradeService.updateGrades(userId, request);
        response.sendRedirect(request.getContextPath() + request.getServletPath() + Paths.USERS);

        return Paths.REDIRECTED;
    }
}
