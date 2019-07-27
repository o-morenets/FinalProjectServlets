package ua.training.admission.controller.command;

import org.apache.log4j.Logger;
import ua.training.admission.model.service.SubjectGradeService;
import ua.training.admission.view.Parameters;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class UpdateGradesCommand extends CommandWrapper {

    private static final Logger log = Logger.getLogger(UpdateSpecialityCommand.class);
    private SubjectGradeService subjectGradeService = SubjectGradeService.getInstance();

    @Override
    String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.valueOf(request.getParameter(Parameters.USER_ID));
        subjectGradeService.updateGrades(userId, request);
        response.sendRedirect(request.getContextPath() + request.getServletPath() + Paths.USERS);

        return Paths.REDIRECTED;
    }
}
