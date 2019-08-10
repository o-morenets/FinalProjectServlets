package ua.training.admission.controller.command.roles.admin;

import org.apache.log4j.Logger;
import ua.training.admission.controller.command.CommandWrapper;
import ua.training.admission.controller.command.roles.user.UpdateSpecialityCommand;
import ua.training.admission.controller.exception.ResourceNotFoundException;
import ua.training.admission.model.entity.SubjectGrade;
import ua.training.admission.model.entity.User;
import ua.training.admission.model.entity.dto.SubjectGradeDto;
import ua.training.admission.model.service.SubjectGradeService;
import ua.training.admission.model.service.UserService;
import ua.training.admission.model.validators.SubjectGradeValidator;
import ua.training.admission.view.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * UpdateGrades Command
 *
 * @author Oleksii Morenets
 */
public class UpdateGradesCommand extends CommandWrapper {

    /* Logger */
    private static final Logger log = Logger.getLogger(UpdateSpecialityCommand.class);

    private SubjectGradeService subjectGradeService = SubjectGradeService.getInstance();
    private UserService userService = UserService.getInstance();

    @Override
    protected String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long userId = Long.parseLong(request.getParameter(Parameters.USER_ID));
        User user = userService.findById(userId).orElseThrow(ResourceNotFoundException::new);

        SubjectGradeValidator subjectGradeValidator = new SubjectGradeValidator();
        Map<String, String> form = extractFormParameters(request);
        validateForm(form, subjectGradeValidator);
        if (subjectGradeValidator.hasErrors()) {
            subjectGradeValidator.setErrorAttributes(request);

            request.setAttribute(Attributes.USER, user);
            request.setAttribute(Attributes.USER_SUBJECT_GRADE_LIST, subjectGradeService.getUserSubjectGradeList(user));
            request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_GRADES);

            return Paths.USER_GRADES_JSP;

        } else {
            subjectGradeService.updateGrades(userId, form);
            response.sendRedirect(request.getContextPath() + request.getServletPath() + Paths.USERS);
        }

        return Paths.REDIRECTED;
    }

    /**
     * Validates entire form
     *
     * @param form                  form parameters
     * @param subjectGradeValidator validator object
     */
    private void validateForm(Map<String, String> form, SubjectGradeValidator subjectGradeValidator) {
        form.forEach((subject, grade) ->
                subjectGradeValidator.validate(new SubjectGradeDto(subject, grade)));
    }

    /**
     * Helper method
     * Extracts form parameters from http request
     *
     * @param request http request
     * @return Map of subject-grade parameters
     */
    private Map<String, String> extractFormParameters(HttpServletRequest request) {
        Map<String, String> form = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String elementName = parameterNames.nextElement();

            if (elementName.startsWith(Constants.PREFIX_SUBJECT)) {
                String paramGrade = request.getParameter(elementName);
                form.put(elementName, paramGrade);
            }
        }

        return form;
    }
}
