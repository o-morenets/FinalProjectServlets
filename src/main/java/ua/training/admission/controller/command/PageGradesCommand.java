package ua.training.admission.controller.command;

import ua.training.admission.model.entity.User;
import ua.training.admission.model.service.SubjectGradeService;
import ua.training.admission.model.service.UserService;
import ua.training.admission.view.Attributes;
import ua.training.admission.view.Paths;
import ua.training.admission.view.TextConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class PageGradesCommand extends CommandWrapper {

    private SubjectGradeService subjectGradeService = SubjectGradeService.getInstance();
    private UserService userService = UserService.getInstance();

    @Override
    String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.valueOf(request.getPathInfo().replaceAll("\\D+", ""));
        final Optional<User> user = userService.findById(userId);
        user.ifPresent(usr -> {
            request.setAttribute(Attributes.USER, usr);
            request.setAttribute("userSubjectGradeList", subjectGradeService.getUserSubjectGradeList(usr));
        });
        request.setAttribute(Attributes.PAGE_TITLE, TextConstants.TITLE_GRADES);

        return Paths.USER_GRADES_JSP;
    }
}
