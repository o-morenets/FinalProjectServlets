package ua.training.admission.controller.command;

import ua.training.admission.view.Attributes;
import ua.training.admission.view.I18n;
import ua.training.admission.view.Paths;
import ua.training.admission.view.TextConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * CommandHolder
 */
public class CommandHolder {

    private Map<String, Command> commands;

    public CommandHolder() {
        initCommands();
    }

    private void initCommands() {
        commands = new HashMap<String, Command>() {
            {
                put(TextConstants.GET + Paths.HOME, new PageHomeCommand());
                put(TextConstants.GET + Paths.LOGIN, new PageLoginCommand());
                put(TextConstants.GET + Paths.SIGNUP, new PageSignupCommand());
                put(TextConstants.GET + Paths.USERS, new UserListCommand());
                put(TextConstants.GET + Paths.USERS_, new UserCommand());
                put(TextConstants.GET + Paths.USERS_SPECIALITY, new PageSpecialityCommand());
                put(TextConstants.GET + Paths.USERS_GRADES, new PageGradesCommand());

                put(TextConstants.POST + Paths.LOGIN, new LoginCommand());
                put(TextConstants.POST + Paths.LOGOUT, new LogoutCommand());
                put(TextConstants.POST + Paths.SIGNUP, new SignupCommand());
                put(TextConstants.POST + Paths.USERS_UPDATE_SPEC, new UpdateSpecialityCommand());
                put(TextConstants.POST + Paths.USERS_UPDATE_GRADES, new UpdateGradesCommand());
            }
        };
    }

    public Command getCommand(String key) {
        return commands.getOrDefault(key, (request, response) -> {
            request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_404);
            response.sendRedirect(request.getContextPath() + Paths.PAGE_404_JSP);
            return Paths.REDIRECTED;
        });
    }
}
