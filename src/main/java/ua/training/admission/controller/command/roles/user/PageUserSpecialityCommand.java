package ua.training.admission.controller.command.roles.user;

import ua.training.admission.controller.command.CommandUtils;
import ua.training.admission.controller.command.CommandWrapper;
import ua.training.admission.controller.exception.ResourceNotFoundException;
import ua.training.admission.model.entity.User;
import ua.training.admission.model.service.SpecialityService;
import ua.training.admission.model.service.UserService;
import ua.training.admission.view.Attributes;
import ua.training.admission.view.I18n;
import ua.training.admission.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * PageUserSpeciality Command
 *
 * @author Oleksii Morenets
 */
public class PageUserSpecialityCommand extends CommandWrapper {

    private SpecialityService specialityService = SpecialityService.getInstance();
    private UserService userService = UserService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long userId = CommandUtils.extractUserIdFromPath(request);
        Optional<User> user = userService.findById(userId);
        User usr = user.orElseThrow(ResourceNotFoundException::new);

        request.setAttribute(Attributes.USER, usr);
        request.setAttribute(Attributes.SPECIALITIES, specialityService.findAll());
        request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_SPECIALITIES);

        return Paths.USER_SPECIALITY_JSP;
    }
}
