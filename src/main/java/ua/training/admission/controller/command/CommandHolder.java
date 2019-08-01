package ua.training.admission.controller.command;

import ua.training.admission.view.Attributes;
import ua.training.admission.view.I18n;
import ua.training.admission.view.Paths;
import ua.training.admission.view.TextConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * CommandHolder
 */
public class CommandHolder {

    /* Command holder map */
    private Map<String, Command> commands;

    /**
     * Constructs Command Holder
     */
    public CommandHolder() {
        initCommands();
    }

    /**
     * Initialize commands
     */
    private void initCommands() {
        commands = new HashMap<String, Command>() {
            {
                put(TextConstants.GET + Paths.HOME, new PageHomeCommand());
                put(TextConstants.GET + Paths.LOGIN, new PageLoginCommand());
                put(TextConstants.GET + Paths.SIGNUP, new PageSignupCommand());
                put(TextConstants.GET + Paths.USERS, new UserListCommand());
                put(TextConstants.GET + Paths.USER_SPECIALITY, new PageUserSpecialityCommand());
                put(TextConstants.GET + Paths.USER_PROFILE, new UserProfileCommand());
                put(TextConstants.GET + Paths.USER_GRADES, new PageUserGradesCommand());

                put(TextConstants.POST + Paths.LOGIN, new LoginCommand());
                put(TextConstants.POST + Paths.LOGOUT, new LogoutCommand());
                put(TextConstants.POST + Paths.SIGNUP, new SignupCommand());
                put(TextConstants.POST + Paths.USERS_UPDATE_SPEC, new UpdateSpecialityCommand());
                put(TextConstants.POST + Paths.USERS_UPDATE_GRADES, new UpdateGradesCommand());
            }
        };
    }

    /**
     * Get command from commands holder
     *
     * @param key command key
     * @return command object or default generated command
     */
    public Command getCommand(String key) {
        return commands.getOrDefault(key, (request, response) -> {
            request.setAttribute(Attributes.PAGE_TITLE, I18n.TITLE_404);

            return Paths.PAGE_404_JSP;
        });
    }
}
