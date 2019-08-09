package ua.training.admission.controller.command.roles.admin;

import org.apache.log4j.Logger;
import ua.training.admission.controller.command.CommandWrapper;
import ua.training.admission.controller.command.roles.user.UpdateSpecialityCommand;
import ua.training.admission.model.service.SubjectGradeService;
import ua.training.admission.view.Attributes;
import ua.training.admission.view.I18n;
import ua.training.admission.view.Parameters;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * PagePassGrade Command
 *
 * @author Oleksii Morenets
 */
public class PagePassGradeCommand extends CommandWrapper {

    /* Logger */
    private static final Logger log = Logger.getLogger(PagePassGradeCommand.class);

    @Override
    protected String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_PASS_GRADE);

        return Paths.PASS_GRADE_JSP;
    }
}
