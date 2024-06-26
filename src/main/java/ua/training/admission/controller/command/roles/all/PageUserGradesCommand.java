package ua.training.admission.controller.command.roles.all;

import org.apache.log4j.Logger;
import ua.training.admission.controller.command.CommandUtils;
import ua.training.admission.controller.command.CommandWrapper;
import ua.training.admission.controller.exception.ResourceNotFoundException;
import ua.training.admission.model.entity.User;
import ua.training.admission.model.service.SubjectGradeService;
import ua.training.admission.model.service.UserService;
import ua.training.admission.view.Attributes;
import ua.training.admission.view.Paths;
import ua.training.admission.view.I18n;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * PageUserGrades Command
 *
 * @author Oleksii Morenets
 */
public class PageUserGradesCommand extends CommandWrapper {

    /* Logger */
    private static final Logger log = Logger.getLogger(UserService.class);

    /* Services */
    private SubjectGradeService subjectGradeService = SubjectGradeService.getInstance();
    private UserService userService = UserService.getInstance();

    @Override
    protected String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long userId = CommandUtils.extractUserIdFromPath(request);
        User user = userService.findById(userId).orElseThrow(ResourceNotFoundException::new);

        request.setAttribute(Attributes.USER, user);
        request.setAttribute(Attributes.USER_SUBJECT_GRADE_LIST, subjectGradeService.getUserSubjectGradeList(user));
        request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_GRADES);

        return Paths.USER_GRADES_JSP;
    }
}
